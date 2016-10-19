package com.zhiyi.hero.resources.action;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.hero.common.BaseController;
import com.zhiyi.hero.common.Result;
import com.zhiyi.hero.platform.api.IPlatformService;
import com.zhiyi.hero.platform.dto.PlatformSearchDto;
import com.zhiyi.hero.resources.action.form.ResourcesAddForm;
import com.zhiyi.hero.resources.action.form.ResourcesUpdateForm;
import com.zhiyi.hero.resources.api.IResourcesService;
import com.zhiyi.hero.resources.dto.ResourcesDto;
import com.zhiyi.hero.resources.dto.ResourcesSearchDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/resources")
public class ResourcesController extends BaseController {

    @Autowired
    private IResourcesService resourcesService;

    @Autowired
    private IPlatformService platformService;

    /**
     * 资源管理首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    @RequiresPermissions("resources:search")
    public String index(ModelMap model) {
        model.put("platforms", platformService.search(
                (PlatformSearchDto) new PlatformSearchDto().disablePaging()).getResults());
        return "resources";
    }


    /**
     * 资源管理列表
     *
     * @param searchDto
     * @param dtReq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @RequiresPermissions("resources:search")
    public DtPagerResponse<ResourcesDto> search(ResourcesSearchDto searchDto, DtRequest dtReq) {
        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        PageSearchResultDto<ResourcesDto, Integer> resultDto = resourcesService.search(searchDto);
        List<ResourcesDto> list = resultDto.getResults();
        int totalSize = resultDto.getMessage();
        return ResponseResult.value(list, totalSize, dtReq);
    }

    @ResponseBody
    @RequestMapping(value = "/simpleSearch", method = {RequestMethod.GET, RequestMethod.POST})
    @RequiresPermissions("resources:search")
    public DtPagerResponse<ResourcesDto> simpleSearch(ResourcesSearchDto searchDto, @RequestParam(defaultValue = "3") Integer level, DtRequest dtReq) {
        PageSearchResultDto<ResourcesDto, Integer> resultDto = resourcesService.search((ResourcesSearchDto) searchDto.disablePaging());
        List<ResourcesDto> results = resultDto.getResults();
        List<ResourcesDto> sortResults = new ArrayList<>();

        //一级
        List<ResourcesDto> oneResources = new ArrayList<>();
        Iterator<ResourcesDto> resultIterator = results.iterator();
        while (resultIterator.hasNext()) {
            ResourcesDto dto = resultIterator.next();
            if (dto.getParentId().intValue() == 0) {
                oneResources.add(dto);
                resultIterator.remove();
            }
        }

        Collections.sort(oneResources, resourceSort());
        //二级
        if (level > 0) {
            for (ResourcesDto oneDto : oneResources) {
                sortResults.add(oneDto);
                List<ResourcesDto> twoResources = new ArrayList<>();
                Long oneParentId = oneDto.getId();
                resultIterator = results.iterator();
                while (resultIterator.hasNext()) {
                    ResourcesDto twoDto = resultIterator.next();
                    if (twoDto.getParentId().longValue() == oneParentId.longValue()) {
                        twoResources.add(twoDto);
                        resultIterator.remove();
                    }
                }
                Collections.sort(twoResources, resourceSort());
                //三级
                if (level > 1) {
                    for (ResourcesDto twoDto : twoResources) {
                        sortResults.add(twoDto);
                        List<ResourcesDto> threeResources = new ArrayList<>();
                        if (level > 2) {
                            Long twoParentId = twoDto.getId();
                            resultIterator = results.iterator();
                            while (resultIterator.hasNext()) {
                                ResourcesDto threeDto = resultIterator.next();
                                if (threeDto.getParentId().longValue() == twoParentId.longValue()) {
                                    threeResources.add(threeDto);
                                    resultIterator.remove();
                                }
                            }
                            Collections.sort(threeResources, resourceSort());
                            sortResults.addAll(threeResources);
                        }
                    }
                }
            }
        }
        return ResponseResult.value(sortResults, resultDto.getMessage(), dtReq);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ResourcesDto get(Long id) {
        return resourcesService.get(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    @RequiresPermissions("resources:add")
    public Result add(ResourcesAddForm addForm) {
        ResourcesDto addDto = addForm.toDto(getLoginUserName());
        resourcesService.save(addDto);
        return ADD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    @RequiresPermissions("resources:update")
    public Result update(ResourcesUpdateForm updateForm) {
        ResourcesDto updateDto = updateForm.toDto(getLoginUserName());
        resourcesService.update(updateDto);
        return UPD_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/searchSubResources")
    @RequiresPermissions("resources:search")
    public List<ResourcesDto> searchSubResources(Long parentId) {
        return resourcesService.searchSubResources(parentId);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteResources")
    @RequiresPermissions("resources:deleteResources")
    public Result deleteResources(String ids) {
        List<Long> IdList = new ArrayList<Long>();
        String[] idsArr = ids.split(",");
        Long[] idsLong = new Long[idsArr.length];
        for (int i = 0; i < idsArr.length; i++) {
            idsLong[i] = Long.valueOf(idsArr[i]);
            IdList.add(idsLong[i]);
        }
        resourcesService.deleteResources(IdList);
        return DEL_SUCCESS;
    }

    private Comparator resourceSort() {
        return new Comparator() {
            @Override
            public int compare(Object arg0, Object arg1) {
                ResourcesDto o1 = (ResourcesDto) arg0;
                ResourcesDto o2 = (ResourcesDto) arg1;
                return (o1.getOrders() < o2.getOrders()) ? -1 : 1;
            }
        };
    }

}

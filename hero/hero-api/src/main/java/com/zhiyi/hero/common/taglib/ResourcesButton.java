package com.zhiyi.hero.common.taglib;

import com.zhiyi.hero.user.dto.PermissionDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;

public class ResourcesButton extends TagSupport {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private String id;

    private String authorize;

    private String className;

    List<PermissionDto> permission = new ArrayList<PermissionDto>();

    private static final long serialVersionUID = -5228534764584086316L;

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            PermissionDto button = null;
            if (permission == null){
                permission = new ArrayList<PermissionDto>();
            }
            OK:
            for (PermissionDto permissionDto : permission) {
                List<PermissionDto> sonList = permissionDto.getPermissions();
                for (PermissionDto sonPermission : sonList) {
                    List<PermissionDto> buttList = sonPermission
                            .getPermissions();
                    for (PermissionDto permission : buttList) {
                        if (StringUtils.equals(permission.getAuthorize(), authorize)) {
                            button = permission;
                            if (button != null) {
                                out.println("<button id='"
                                        + id
                                        + "' class='"
                                        + className
                                        + "' path='"
                                        + pageContext.getSession()
                                        .getServletContext()
                                        .getContextPath()
                                        + button.getLinkUrl()
                                        + "' authorize = '" + authorize + "' type='button'>"
                                        + button.getName() + "</button>");
                            }
                            break OK;
                        }
                    }
                }
            }

        } catch (Exception e) {
//            throw new JspException(e.getMessage());
            logger.error("JSPException",e);
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public List<PermissionDto> getPermission() {
        return permission;
    }

    public void setPermission(List<PermissionDto> permission) {
        this.permission = permission;
    }
}

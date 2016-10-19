package com.zhiyi.hero.common.taglib;

import com.zhiyi.hero.user.dto.PermissionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourcesMenu extends TagSupport {

    private static final long serialVersionUID = 6086112980788216757L;

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    List<PermissionDto> permission = new ArrayList<PermissionDto>();

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = this.pageContext.getOut();
        try {
            HttpSession session = this.pageContext.getSession();
            HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
            String contextPath = session.getServletContext().getContextPath();
            out.println("<ul class='nav nav-list' id='menu-nav-list'>");
            if (permission == null) {
                permission = new ArrayList<PermissionDto>();
            }
            for (int i = 0; i < permission.size(); i++) {
                PermissionDto per = permission.get(i);
                out.println("<li>");
                out.println("<a href='#' class='dropdown-toggle'>");
                if (i % 2 == 0) {
                    out.println("<i class='icon-list'></i>");
                } else {
                    out.println("<i class='icon-calendar'></i>");
                }
                out.println("<span class='menu-text'>" + per.getName() + "</span>");
                out.println("<b class='arrow icon-angle-down'></b>");
                out.println(" </a>");
                out.println("<ul class='submenu'>");
                List<PermissionDto> sonList = per.getPermissions();
                for (PermissionDto sonPermission : sonList) {
                    out.println(" <li><a href=" + contextPath + sonPermission.getLinkUrl() + ">" +
                            "<i class='icon-double-angle-right'></i>" + sonPermission.getName() + "</a></li>");
                }
                out.println("</ul>");
                out.println("</li>");
            }
            out.println("</ul>");
        } catch (Exception e) {
            logger.error("ResourcesMenu doStartTag error", e);
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

    public List<PermissionDto> getPermission() {
        return permission;
    }

    public void setPermission(List<PermissionDto> permission) {
        this.permission = permission;
    }

}

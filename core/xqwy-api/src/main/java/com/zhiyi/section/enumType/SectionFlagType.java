package com.zhiyi.section.enumType;

/**
 * 板块sectionFlag值描述
 */
public enum SectionFlagType {
	SECTION_FLAG_TYPE("SECTION_FLAG_TYPE"),UN_SECTION_FLAG_TYPE("UN_SECTION_FLAG_TYPE");

	private String status;

	SectionFlagType(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}

package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 */
@SuppressWarnings("serial")
public class DeliveryDataCommunityUnitPictureDto implements Serializable {

	private Integer deliveryDataCommunityUnitId;
	private Sequence sequence;
	private String path;
	private Date saveDate;
	
	public DeliveryDataCommunityUnitPictureDto(){}
	
	public DeliveryDataCommunityUnitPictureDto(
		 int deliveryDataCommunityUnitId
		,Sequence sequence
		,String path
		,Date saveDate			
	){
		this.deliveryDataCommunityUnitId = deliveryDataCommunityUnitId;
		this.sequence = sequence;
		this.path = path;
		this.saveDate = saveDate;
	}
	
	public enum Sequence{
		_0(0),_1(1),_2(2),_3(3);
		private int value;
		Sequence(int value){
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
		public static Sequence get( int v ){
			switch (v) {
				case 0:return _0;
				case 1:return _1;
				case 2:return _2;
				case 3:return _3;
				default:throw new RuntimeException("非法的枚举:["+v+"]");
			}
		}
	}
	
	public Integer getDeliveryDataCommunityUnitId() {
		return deliveryDataCommunityUnitId;
	}
	public void setDeliveryDataCommunityUnitId(Integer deliveryDataCommunityUnitId) {
		this.deliveryDataCommunityUnitId = deliveryDataCommunityUnitId;
	}
	public Integer getSequence() {
		return sequence.getValue();
	}
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
	public void setSequence(int sequenceValue) {
		this.sequence = Sequence.get(sequenceValue);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	
	
}

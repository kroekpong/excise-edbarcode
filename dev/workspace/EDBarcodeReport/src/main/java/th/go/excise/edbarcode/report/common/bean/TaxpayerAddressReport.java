package th.go.excise.edbarcode.report.common.bean;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxpayerAddressReport {

	private String houseIdNumber;
	private String buildingName;
	private String roomNumber;
	private String floorNumber;
	private String villageName;
	private String houseNumber;
	private String mooNumber;
	private String trokSoiName;
	private String streetName;
	private String thambolName;
	private String amphurName;
	private String provinceName;
	private String postcode;
	private String telNumber;
	
	
	@XmlElement(name = "HouseIdNumber")
	public String getHouseIdNumber() {
		return houseIdNumber;
	}

	public void setHouseIdNumber(String houseIdNumber) {
		this.houseIdNumber = houseIdNumber;
	}

	@XmlElement(name = "BuildingName")
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	@XmlElement(name = "RoomNumber")
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@XmlElement(name = "FloorNumber")
	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	@XmlElement(name = "VillageName")
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	@XmlElement(name = "HouseNumber")
	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@XmlElement(name = "MooNumber")
	public String getMooNumber() {
		return mooNumber;
	}

	public void setMooNumber(String mooNumber) {
		this.mooNumber = mooNumber;
	}

	@XmlElement(name = "TrokSoiName")
	public String getTrokSoiName() {
		return trokSoiName;
	}

	public void setTrokSoiName(String trokSoiName) {
		this.trokSoiName = trokSoiName;
	}

	@XmlElement(name = "StreetName")
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@XmlElement(name = "ThambolName")
	public String getThambolName() {
		return thambolName;
	}

	public void setThambolName(String thambolName) {
		this.thambolName = thambolName;
	}

	@XmlElement(name = "AmphurName")
	public String getAmphurName() {
		return amphurName;
	}

	public void setAmphurName(String amphurName) {
		this.amphurName = amphurName;
	}

	@XmlElement(name = "ProvinceName")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@XmlElement(name = "Postcode")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@XmlElement(name = "TelNumber")
	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}

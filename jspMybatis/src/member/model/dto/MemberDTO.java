package member.model.dto;

import java.sql.Timestamp;

public class MemberDTO {
	private int no;
	private String id;
	private String passwd;
	private String passwdChk;
	private String name;
	private String gender;
	private int bornYear;
	private String postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private Timestamp regiDate;
	private String email;
	
	
	public MemberDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdChk() {
		return passwdChk;
	}

	public void setPasswdChk(String passwdChk) {
		this.passwdChk = passwdChk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBornYear() {
		return bornYear;
	}

	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public Timestamp getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Timestamp regiDate) {
		this.regiDate = regiDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [no=" + no + ", id=" + id + ", passwd=" + passwd + ", passwdChk=" + passwdChk + ", name="
				+ name + ", gender=" + gender + ", bornYear=" + bornYear + ", postcode=" + postcode + ", address="
				+ address + ", detailAddress=" + detailAddress + ", extraAddress=" + extraAddress + ", regiDate="
				+ regiDate + ", email=" + email + "]";
	}
	
}

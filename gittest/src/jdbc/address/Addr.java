package jdbc.address;
//>>240311-4th
//주소록
//시퀀스:seq_addr
//테이블:add(
//num(number, primary key, 자동할당),
//name:varchar2(20), 중복허용,
//tel:varchar2(20),
//addr:varchar2(30),
//)
//vo: Addr
//dao:
//insert: 한명 추가. >> 파람으로 vo받아서, 서비스에서 사용할 거면 반환이 필요
//update: 번호로 찾아서 전화, 주소 수정
//delete: 번호로 찾아서 삭제
//select: 번호로 찾아서 vo 반환
//selectAll: 전체검색
//selectByName: name으로 검색(like 패턴으로 검색). 여러 줄 검색. ArrayList에 담아서 반환
//selectByTel: tel로 검색(like 패턴으로 검색). 여러 줄 검색. ArrayList에 담아서 반환

//>>vo
public class Addr {
	private int num;
	private String name;
	private String tel;
	private String addr;
	
	public Addr(){
		
	}

	public Addr(int num, String name, String tel, String addr) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Addr [num=" + num + ", name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
}

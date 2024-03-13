package jdbc.address;

import java.util.Scanner;

public class AddrMenu {
	private AddrService service;
	
	public AddrMenu() {
		service = new AddrService();
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1.등록 2.번호로검색 3.이름으로검색 4.전화로검색 5.수정 6.삭제 7.전체목록 8.종료");
			int m = sc.nextInt();
			switch(m) {
			case 1:
				service.addAddr(sc);
				break;
			case 2:
				service.printAddr(sc);
				break;
			case 3:
				service.printByName(sc);
				break;
			case 4:
				service.printByTel(sc);
				break;
			case 5:
				service.editAddr(sc);
				break;
			case 6:
				service.delAddr(sc);
				break;
			case 7:
				service.printAll();
				break;
			case 8:
				flag = false;
				break;
			}
		}
	}
}

package jdbc.address;

import java.util.ArrayList;
import java.util.Scanner;

//>>240311-5th
public class AddrService {
	private AddrDao dao;

	public AddrService() {
		dao = new AddrDao();
	}

	// 1명의 이름, 전화, 주소 입력 받아서 db에 저장
	public void addAddr(Scanner sc) {
		System.out.println("=== 등록 ===");
		System.out.print("name:");
		String name = sc.next();
		System.out.print("tel:");
		String tel = sc.next();
		System.out.print("addr:");
//		String addr = sc.next();
		sc.nextLine();// 버퍼에 남은 엔터 삭제 >>버퍼 엔터 지우기 용도
		String addr = sc.nextLine(); // 공백 포함한 한 줄 입력
		// >>??
		dao.insert(new Addr(0, name, tel, addr)); // >>번호 자동할당 아무 숫자 기입, 사용되지 않는 숫자
	}

	// 수정할 사람 번호와 새 전화, 주소를 입력 받아 db에 수정
	public void editAddr(Scanner sc) {
		System.out.println("=== 수정 ===");
		System.out.print("수정할 num:");
		int num = sc.nextInt();

		System.out.print("new tel:");
		String tel = sc.next();
		System.out.print("new addr:");
		String addr = sc.next();
		int cnt = dao.update(new Addr(num, "", tel, addr));
		if (cnt > 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 취소");
		}
	}

	// 삭제 할 사람 번호를 입력 받아 db에서 삭제
	public void delAddr(Scanner sc) {
		System.out.println("=== 삭제 ===");
		System.out.print("삭제할 num:");
		int num = sc.nextInt();

		int cnt = dao.delete(num);
		if (cnt > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제가 취소");
		}
	}

	// 검색할 사람 번호를 입력받아 db에서 검색하고 null이 아니면 출력
	public void printAddr(Scanner sc) {
		System.out.println("=== 번호로 검색 ===");
		System.out.print("검색할 num:");
		int num = sc.nextInt();
		Addr a = dao.select(num);
		if (a == null) {
			System.out.println("없는 번호");
		} else {
			System.out.println(a);
		}

	}

	// 전체 주소 목록 출력
	public void printAll() {
		ArrayList<Addr> list = dao.selectAll();
		System.out.println("=== 전체 목록 ===");
		for (Addr a : list) {
			System.out.println(a);
		}

	}

	// 이름으로 검색, 검색할 사람 이름 입력 받아서 db에서 이름으로 검색하고 결과를 출력
	public void printByName(Scanner sc) {
		System.out.println("=== 이름으로 검색 ===");
		System.out.print("검색할 name:");
		String name = sc.next();
		ArrayList<Addr> list = dao.selectByName(name);
		for (Addr a : list) {
			System.out.println(a);
		}
	}

	// 검색할 사람 전화 입력 받아서 db에서 전화로 검색하고 결과를 출력
	public void printByTel(Scanner sc) {
		System.out.println("=== 번호검색 ===");
		System.out.print("검색할 tel:");
		String tel = sc.next();
		ArrayList<Addr> list = dao.selectByTel(tel);
		for (Addr a : list) {
			System.out.println(a);
		}
	}
}

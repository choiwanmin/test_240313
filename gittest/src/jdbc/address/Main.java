package jdbc.address;
//>>로그인 기능, 게시판 글 작성 기능 추가
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
//		AddrService service = new AddrService();
//		service.addAddr(sc);
		AddrMenu menu = new AddrMenu();
		menu.run(sc);
	}
}

package jdbc.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//>>240311-4th

//dao:
//insert: 한명 추가. >> 파람으로 vo받아서, 서비스에서 사용할 거면 반환이 필요
//update: 번호로 찾아서 전화, 주소 수정
//delete: 번호로 찾아서 삭제
//select: 번호로 찾아서 vo 반환
//selectAll: 전체검색
//selectByName: name으로 검색(like 패턴으로 검색). 여러 줄 검색. ArrayList에 담아서 반환
//selectByTel: tel로 검색(like 패턴으로 검색). 여러 줄 검색. ArrayList에 담아서 반환

//>>dao
public class AddrDao {
	private DBConnect db;

	public AddrDao() {
		db = DBConnect.getInstance();
	}

	// 추가
	public int insert(Addr a) {
		Connection conn = db.conn();

		String sql = "insert into addr values(seql_addr.nextval,?,?,?)";

		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getTel());
			pstmt.setString(3, a.getAddr());

			cnt = pstmt.executeUpdate();

			System.out.println(cnt + "줄 추가됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt; // >>몇개의 행이 처리되었는지 알려줌
	}

	// 수정
	public int update(Addr a) {
		Connection conn = db.conn();
		String sql = "update addr set tel = ?, addr = ? where num = ?";

		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getTel());
			pstmt.setString(2, a.getAddr());
			pstmt.setInt(3, a.getNum());

			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 수정됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// 삭제
	public int delete(int num) {
		Connection conn = db.conn();
		String sql = "delete from addr where num = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 삭제됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt; // >>정상처리된 행수를 반환해줌
	}

	// 검색(번호)
	public Addr select(int num) {
		Connection conn = db.conn();
		String sql = "select * from addr where num = ?";
//		Addr addr = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
//				addr = new Addr();
//				addr.setNum(rs.getInt(1));
//				addr.setName(rs.getString(2));
//				addr.setTel(rs.getString(3));
//				addr.setAddr(rs.getString(4));
				return new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		return addr;
		return null;
	}

	// 전체검색
	public ArrayList<Addr> selectAll() {
		Connection conn = db.conn();
		String sql = "select * from addr order by num";

		ArrayList<Addr> list = new ArrayList<Addr>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) { // >>행 방문 메소드 무한 반복
//				Addr addr = new Addr();
//				addr.setNum(rs.getInt(1));
//				addr.setName(rs.getString(2));
//				addr.setTel(rs.getString(3));
//				addr.setAddr(rs.getString(4));
//				list.add(addr);
				list.add(new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 검색(이름)
	public ArrayList<Addr> selectByName(String name) {
		Connection conn = db.conn();
		String sql = "select * from addr where name like ?";
		ArrayList<Addr> list = new ArrayList<Addr>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Addr addr = new Addr();
				addr = new Addr();
				addr.setNum(rs.getInt(1));
				addr.setName(rs.getString(2));
				addr.setTel(rs.getString(3));
				addr.setAddr(rs.getString(4));
				list.add(addr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 검색(전화번호)
	public ArrayList<Addr> selectByTel(String tel) {
		Connection conn = db.conn();
		String sql = "select * from addr where tel like ?";

		ArrayList<Addr> list = new ArrayList<Addr>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + tel + "%");

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Addr addr = new Addr();
				addr = new Addr();
				addr.setNum(rs.getInt(1));
				addr.setName(rs.getString(2));
				addr.setTel(rs.getString(3));
				addr.setAddr(rs.getString(4));
				list.add(addr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}

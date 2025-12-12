package quanlysinhvien;

import java.util.Scanner;

public class SinhVienCaoDang extends SinhVien {
    double diemThi;
    String xepLoai;
    
    @Override
    public void nhapThongTinRieng(Scanner scanner) {
        this.sc = scanner;
        
        // Nhập điểm thi
        while (true) {
            System.out.print("Nhap diem thi (0-10): ");
            String diemStr = sc.nextLine();
            if (kiemTraDiem(diemStr)) {
                diemThi = chuyenChuoiThanhSoThapPhan(diemStr);
                break;
            }
            System.out.println("LOI: Diem phai tu 0 den 10!");
        }
        
        tinhXepLoai();
    }
    
    @Override
    public void tinhXepLoai() {
        if (diemThi >= 8.5) {
            xepLoai = "Gioi";
        } else if (diemThi >= 7.0) {
            xepLoai = "Kha";
        } else if (diemThi >= 5.5) {
            xepLoai = "TB";
        } else {
            xepLoai = "Yeu";
        }
    }
    
    @Override
    public boolean duocThuong() {
        return xepLoai.equals("Gioi");
    }
    
    @Override
    public void xuatThongTinRieng() {
        System.out.println("He: Cao Dang");
        System.out.println("Diem thi: " + diemThi);
        System.out.println("Xep loai: " + xepLoai);
    }
}
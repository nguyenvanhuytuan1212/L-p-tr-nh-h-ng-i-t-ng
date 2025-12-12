package quanlysinhvien;

import java.util.Scanner;

public abstract class SinhVien {
    String maSV;
    String hoTen;
    String gioiTinh;
    String ngaySinh;
    Scanner sc;
    
    // Hàm chuyển chữ thường tự viết
    char chuyenThuongKyTu(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char)(c + 32);
        }
        return c;
    }
    
    // Chuyển cả chuỗi thành chữ thường
    String chuyenThuongChuoi(String s) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = chuyenThuongKyTu(s.charAt(i));
        }
        return new String(arr);
    }
    
    // Kiểm tra mã SV chỉ chữ và số
    boolean kiemTraMaSV(String ma) {
        if (ma.length() == 0) return false;
        for (int i = 0; i < ma.length(); i++) {
            char c = ma.charAt(i);
            if (!((c >= 'a' && c <= 'z') || 
                  (c >= 'A' && c <= 'Z') || 
                  (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        return true;
    }
    
    // Kiểm tra họ tên chỉ chữ và khoảng trắng
    boolean kiemTraHoTen(String ten) {
        if (ten.length() == 0) return false;
        for (int i = 0; i < ten.length(); i++) {
            char c = ten.charAt(i);
            // Kiểm tra có phải chữ cái không (kể cả có dấu)
            // Hoặc khoảng trắng
            if (!((c >= 'a' && c <= 'z') || 
                  (c >= 'A' && c <= 'Z') ||
                  c == ' ' || c == '\t')) {
                return false;
            }
        }
        return true;
    }
    
    // Kiểm tra giới tính
    boolean kiemTraGioiTinh(String gt) {
        String gtThuong = chuyenThuongChuoi(gt);
        return gtThuong.equals("nam") || gtThuong.equals("nu") || 
               gtThuong.equals("nữ");
    }
    
    // Kiểm tra ngày tháng năm tự viết
    boolean kiemTraNgaySinh(String ngay) {
        // Kiểm tra độ dài
        if (ngay.length() != 10) return false;
        
        // Kiểm tra định dạng dd/mm/yyyy
        if (ngay.charAt(2) != '/' || ngay.charAt(5) != '/') return false;
        
        // Tách ngày, tháng, năm
        String ngayStr = ngay.substring(0, 2);
        String thangStr = ngay.substring(3, 5);
        String namStr = ngay.substring(6, 10);
        
        // Kiểm tra toàn số
        if (!laSoNguyen(ngayStr) || !laSoNguyen(thangStr) || !laSoNguyen(namStr)) {
            return false;
        }
        
        // Chuyển thành số
        int ngaySo = chuyenChuoiThanhSo(ngayStr);
        int thangSo = chuyenChuoiThanhSo(thangStr);
        int namSo = chuyenChuoiThanhSo(namStr);
        
        // Kiểm tra hợp lệ
        if (namSo < 1900 || namSo > 2024) return false;
        if (thangSo < 1 || thangSo > 12) return false;
        
        // Kiểm tra ngày theo tháng
        int[] ngayTrongThang = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Kiểm tra năm nhuận
        if (namSo % 400 == 0 || (namSo % 4 == 0 && namSo % 100 != 0)) {
            ngayTrongThang[1] = 29;
        }
        
        if (ngaySo < 1 || ngaySo > ngayTrongThang[thangSo - 1]) {
            return false;
        }
        
        return true;
    }
    
    // Kiểm tra chuỗi có phải số nguyên không
    boolean laSoNguyen(String s) {
        if (s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
    // Chuyển chuỗi thành số tự viết
    int chuyenChuoiThanhSo(String s) {
        int so = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            so = so * 10 + (c - '0');
        }
        return so;
    }
    
    // Kiểm tra điểm số từ 0 đến 10
    boolean kiemTraDiem(String diemStr) {
        // Kiểm tra có phải số thập phân không
        boolean coDauCham = false;
        for (int i = 0; i < diemStr.length(); i++) {
            char c = diemStr.charAt(i);
            if (c == '.') {
                if (coDauCham) return false; // Có 2 dấu chấm
                coDauCham = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        
        // Chuyển thành số
        double diem = chuyenChuoiThanhSoThapPhan(diemStr);
        return diem >= 0 && diem <= 10;
    }
    
    // Chuyển chuỗi thành số thập phân tự viết
    double chuyenChuoiThanhSoThapPhan(String s) {
        double so = 0;
        double phanThapPhan = 0;
        boolean daQuaDauCham = false;
        double viTriThapPhan = 1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.') {
                daQuaDauCham = true;
            } else if (!daQuaDauCham) {
                so = so * 10 + (c - '0');
            } else {
                phanThapPhan = phanThapPhan * 10 + (c - '0');
                viTriThapPhan *= 10;
            }
        }
        
        return so + phanThapPhan / viTriThapPhan;
    }
    
    public void nhapThongTinChung(Scanner scanner) {
        this.sc = scanner;
        
        // Nhập mã SV
        while (true) {
            System.out.print("Nhap ma SV: ");
            maSV = sc.nextLine();
            if (kiemTraMaSV(maSV)) {
                break;
            }
            System.out.println("LOI: Ma SV chi duoc chu va so!");
        }
        
        // Nhập họ tên
        while (true) {
            System.out.print("Nhap ho ten: ");
            hoTen = sc.nextLine();
            if (kiemTraHoTen(hoTen)) {
                break;
            }
            System.out.println("LOI: Ho ten chi duoc chu!");
        }
        
        // Nhập giới tính
        while (true) {
            System.out.print("Nhap gioi tinh (nam/nu): ");
            gioiTinh = sc.nextLine();
            if (kiemTraGioiTinh(gioiTinh)) {
                break;
            }
            System.out.println("LOI: Gioi tinh phai la 'nam' hoac 'nu'!");
        }
        
        // Nhập ngày sinh
        while (true) {
            System.out.print("Nhap ngay sinh (dd/mm/yyyy): ");
            ngaySinh = sc.nextLine();
            if (kiemTraNgaySinh(ngaySinh)) {
                break;
            }
            System.out.println("LOI: Ngay sinh phai dung dinh dang dd/mm/yyyy!");
        }
    }
    
    public void xuatThongTinChung() {
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Gioi tinh: " + gioiTinh);
        System.out.println("Ngay sinh: " + ngaySinh);
    }
    
    public abstract void nhapThongTinRieng(Scanner sc);
    public abstract void xuatThongTinRieng();
    public abstract void tinhXepLoai();
    public abstract boolean duocThuong();
}
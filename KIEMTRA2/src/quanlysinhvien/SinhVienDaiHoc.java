package quanlysinhvien;

import java.util.Scanner;

public class SinhVienDaiHoc extends SinhVien {
    int tinChi;
    String deTai;
    double diemDG;
    String xepLoai;
    
    // Kiểm tra số tín chỉ
    boolean kiemTraTinChi(String tcStr) {
        if (!laSoNguyen(tcStr)) return false;
        int tc = chuyenChuoiThanhSo(tcStr);
        return tc > 0 && tc <= 200;
    }
    
    // Kiểm tra điểm đánh giá từ 0-5
    boolean kiemTraDiemDG(String diemStr) {
        // Kiểm tra có phải số thập phân không
        boolean coDauCham = false;
        for (int i = 0; i < diemStr.length(); i++) {
            char c = diemStr.charAt(i);
            if (c == '.') {
                if (coDauCham) return false;
                coDauCham = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        
        // Chuyển thành số
        double diem = chuyenChuoiThanhSoThapPhan(diemStr);
        return diem >= 0 && diem <= 5;
    }
    
    @Override
    public void nhapThongTinRieng(Scanner scanner) {
        this.sc = scanner;
        
        // Nhập số tín chỉ
        while (true) {
            System.out.print("Nhap so tin chi (1-200): ");
            String tcStr = sc.nextLine();
            if (kiemTraTinChi(tcStr)) {
                tinChi = chuyenChuoiThanhSo(tcStr);
                break;
            }
            System.out.println("LOI: Tin chi phai la so tu 1 den 200!");
        }
        
        // Nhập đề tài
        while (true) {
            System.out.print("Nhap ten de tai: ");
            deTai = sc.nextLine();
            if (deTai.length() > 0) {
                break;
            }
            System.out.println("LOI: Ten de tai khong duoc de trong!");
        }
        
        // Nhập điểm đánh giá
        while (true) {
            System.out.print("Nhap diem danh gia (0-5): ");
            String diemStr = sc.nextLine();
            if (kiemTraDiemDG(diemStr)) {
                diemDG = chuyenChuoiThanhSoThapPhan(diemStr);
                break;
            }
            System.out.println("LOI: Diem danh gia phai tu 0 den 5!");
        }
        
        tinhXepLoai();
    }
    
    @Override
    public void tinhXepLoai() {
        if (diemDG >= 4.5) {
            xepLoai = "A";
        } else if (diemDG >= 3.5) {
            xepLoai = "B";
        } else if (diemDG >= 2.5) {
            xepLoai = "C";
        } else {
            xepLoai = "D";
        }
    }
    
    @Override
    public boolean duocThuong() {
        return xepLoai.equals("A");
    }
    
    @Override
    public void xuatThongTinRieng() {
        System.out.println("He: Dai hoc");
        System.out.println("Tin chi: " + tinChi);
        System.out.println("De tai: " + deTai);
        System.out.println("Diem DG: " + diemDG);
        System.out.println("Xep loai: " + xepLoai);
    }
}
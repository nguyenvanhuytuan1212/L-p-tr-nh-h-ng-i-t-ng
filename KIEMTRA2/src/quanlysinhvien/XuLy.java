package quanlysinhvien;

import java.util.Scanner;

public class XuLy {
    SinhVien[] ds;
    int n;
    Scanner sc;
    
    public XuLy(Scanner scanner) {
        sc = scanner;
        ds = new SinhVien[100];
        n = 0;
    }
    
    // 1. Nhập thông tin sinh viên
    public void nhapSV() {
        if (n >= 100) {
            System.out.println("Danh sach da day!");
            return;
        }
        
        System.out.println("\n--- NHAP SINH VIEN ---");
        System.out.println("1. Trung Cap");
        System.out.println("2. Cao Dang");
        System.out.println("3. Dai hoc");
        System.out.print("Chon: ");
        
        String c;
        int chon = 0;
        while (true) {
            c = sc.nextLine();
            // Kiểm tra có phải số không
            boolean laSo = true;
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) < '0' || c.charAt(i) > '9') {
                    laSo = false;
                    break;
                }
            }
            
            if (laSo && c.length() > 0) {
                chon = 0;
                for (int i = 0; i < c.length(); i++) {
                    chon = chon * 10 + (c.charAt(i) - '0');
                }
                if (chon >= 1 && chon <= 3) {
                    break;
                }
            }
            System.out.println("LOI: Chon 1, 2 hoac 3!");
            System.out.print("Chon lai: ");
        }
        
        SinhVien sv = null;
        
        if (chon == 1) {
            sv = new SinhVienTrungCap();
        } else if (chon == 2) {
            sv = new SinhVienCaoDang();
        } else if (chon == 3) {
            sv = new SinhVienDaiHoc();
        }
        
        sv.nhapThongTinChung(sc);
        sv.nhapThongTinRieng(sc);
        
        ds[n] = sv;
        n++;
        System.out.println("Da nhap xong!");
    }
    
    // 2. Xuất thông tin đầy đủ
    public void xuatTatCa() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        
        System.out.println("\n--- DANH SACH SINH VIEN ---");
        for (int i = 0; i < n; i++) {
            System.out.println("\nSinh vien " + (i+1) + ":");
            ds[i].xuatThongTinChung();
            ds[i].xuatThongTinRieng();
            System.out.println("---------");
        }
    }
    
    // 3. Sắp xếp theo mã (riêng từng hệ)
    public void sapXepTheoMa() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        
        // Sắp xếp tất cả
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // So sánh 2 chuỗi bằng tay
                String ma1 = ds[i].maSV;
                String ma2 = ds[j].maSV;
                boolean canDoi = false;
                
                // So sánh từng ký tự
                int minLen = ma1.length() < ma2.length() ? ma1.length() : ma2.length();
                for (int k = 0; k < minLen; k++) {
                    if (ma1.charAt(k) > ma2.charAt(k)) {
                        canDoi = true;
                        break;
                    } else if (ma1.charAt(k) < ma2.charAt(k)) {
                        break;
                    }
                }
                
                // Nếu bằng nhau đến minLen thì xét độ dài
                if (!canDoi && ma1.length() > ma2.length()) {
                    canDoi = true;
                }
                
                if (canDoi) {
                    SinhVien temp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = temp;
                }
            }
        }
        
        System.out.println("Da sap xep!");
        hienThiTheoHe();
    }
    
    // Hiển thị theo từng hệ
    private void hienThiTheoHe() {
        System.out.println("\n--- THEO TUNG HE ---");
        
        System.out.println("\n1. TRUNG CAP:");
        boolean coTC = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienTrungCap) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coTC = true;
            }
        }
        if (!coTC) System.out.println("Khong co");
        
        System.out.println("\n2. CAO DANG:");
        boolean coCD = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienCaoDang) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coCD = true;
            }
        }
        if (!coCD) System.out.println("Khong co");
        
        System.out.println("\n3. DAI HOC:");
        boolean coDH = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienDaiHoc) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coDH = true;
            }
        }
        if (!coDH) System.out.println("Khong co");
    }
    
    // 4. Thống kê tổng số
    public void thongKe() {
        int tc = 0, cd = 0, dh = 0;
        
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienTrungCap) {
                tc++;
            } else if (ds[i] instanceof SinhVienCaoDang) {
                cd++;
            } else if (ds[i] instanceof SinhVienDaiHoc) {
                dh++;
            }
        }
        
        System.out.println("\n--- THONG KE ---");
        System.out.println("Tong: " + n);
        System.out.println("Trung Cap: " + tc);
        System.out.println("Cao Dang: " + cd);
        System.out.println("Dai hoc: " + dh);
    }
    
    // Hàm chuyển chữ thường tự viết
    private String chuyenThuongChuoi(String s) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                arr[i] = (char)(c + 32);
            } else {
                arr[i] = c;
            }
        }
        return new String(arr);
    }
    
    // 5. Tìm kiếm theo tên
    public void timTheoTen() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        
        System.out.print("\nNhap ten can tim: ");
        String ten = sc.nextLine();
        
        boolean timThay = false;
        System.out.println("\n--- KET QUA TIM KIEM ---");
        
        // Chuyển tên cần tìm thành chữ thường
        String tenCanTim = chuyenThuongChuoi(ten);
        
        for (int i = 0; i < n; i++) {
            // Chuyển họ tên sinh viên thành chữ thường
            String hotenSV = chuyenThuongChuoi(ds[i].hoTen);
            
            // Tìm kiếm chuỗi con bằng tay
            boolean coTen = false;
            for (int j = 0; j <= hotenSV.length() - tenCanTim.length(); j++) {
                boolean trung = true;
                for (int k = 0; k < tenCanTim.length(); k++) {
                    if (hotenSV.charAt(j + k) != tenCanTim.charAt(k)) {
                        trung = false;
                        break;
                    }
                }
                if (trung) {
                    coTen = true;
                    break;
                }
            }
            
            if (coTen) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                timThay = true;
            }
        }
        
        if (!timThay) {
            System.out.println("Khong tim thay!");
        }
    }
    
    // 6. Danh sách được thưởng
    public void dsDuocThuong() {
        if (n == 0) {
            System.out.println("Danh sach trong!");
            return;
        }
        
        System.out.println("\n--- DANH SACH DUOC THUONG ---");
        
        System.out.println("\n1. TRUNG CAP (Gioi):");
        boolean coTC = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienTrungCap && ds[i].duocThuong()) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coTC = true;
            }
        }
        if (!coTC) System.out.println("Khong co");
        
        System.out.println("\n2. CAO DANG (Gioi):");
        boolean coCD = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienCaoDang && ds[i].duocThuong()) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coCD = true;
            }
        }
        if (!coCD) System.out.println("Khong co");
        
        System.out.println("\n3. DAI HOC (A):");
        boolean coDH = false;
        for (int i = 0; i < n; i++) {
            if (ds[i] instanceof SinhVienDaiHoc && ds[i].duocThuong()) {
                ds[i].xuatThongTinChung();
                ds[i].xuatThongTinRieng();
                System.out.println("---------");
                coDH = true;
            }
        }
        if (!coDH) System.out.println("Khong co");
    }
}
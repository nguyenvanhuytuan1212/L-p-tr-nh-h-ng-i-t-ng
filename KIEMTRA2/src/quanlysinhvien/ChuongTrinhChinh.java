package quanlysinhvien;

import java.util.Scanner;

public class ChuongTrinhChinh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        XuLy xl = new XuLy(sc);
        
        int chon;
        
        while (true) {
            hienMenu();
            System.out.print("Chon: ");
            String s = sc.nextLine();
            
            // Chuyển chuỗi thành số và kiểm tra
            chon = 0;
            boolean laSoHopLe = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    chon = chon * 10 + (c - '0');
                } else {
                    laSoHopLe = false;
                    break;
                }
            }
            
            if (!laSoHopLe) {
                System.out.println("LOI: Vui long nhap so!");
                continue;
            }
            
            if (chon == 0) {
                System.out.print("Ban co chac muon thoat? (y/n): ");
                String xacNhan = sc.nextLine();
                if (xacNhan.equalsIgnoreCase("y") || xacNhan.equalsIgnoreCase("yes")) {
                    break;
                }
                continue;
            }
            
            switch (chon) {
                case 1:
                    xl.nhapSV();
                    break;
                case 2:
                    xl.xuatTatCa();
                    break;
                case 3:
                    xl.sapXepTheoMa();
                    break;
                case 4:
                    xl.thongKe();
                    break;
                case 5:
                    xl.timTheoTen();
                    break;
                case 6:
                    xl.dsDuocThuong();
                    break;
                default:
                    System.out.println("LOI: Chon tu 0 den 6!");
            }
            
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
        }
        
        System.out.println("\nCam on da su dung chuong trinh!");
    }
    
    static void hienMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Nhap sinh vien");
        System.out.println("2. Xuat tat ca sinh vien");
        System.out.println("3. Sap xep theo ma (rieng tung he)");
        System.out.println("4. Thong ke tong so");
        System.out.println("5. Tim kiem theo ten");
        System.out.println("6. Danh sach duoc thuong");
        System.out.println("0. Thoat");
        System.out.println("==========================");
    }
}
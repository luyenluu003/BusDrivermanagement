import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class main {

    private static List<LaiXe> laiXes = new ArrayList<>();
    private static List<Tuyen> tuyens = new ArrayList<>();
    private static List<BangPhanCong> bangPhanCongs = new ArrayList<>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\nBus Driver Managenment");
            System.out.println("1. Thêm lái xe");
            System.out.println("2. Hiển thị lái xe");
            System.out.println("3. Thêm tuyến");
            System.out.println("4. Hiển thị tuyến");
            System.out.println("5. Lập bảng phân công");
            System.out.println("6. Hiển thị bảng phân công");
            System.out.println("7. Sắp xếp bảng phân công theo họ tên");
            System.out.println("8. Sắp xếp theo số lượng tuyến đảm nhận trong ngày");
            System.out.println("9. Bảng thống kê tổng khoảng cách xe chạy của mỗi lái xe");
            System.out.println("10. Lưu vào file");
            System.out.println("0. Thoát...");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    themLaiXe();
                    pause();
                    break;
                case 2:
                    hienThiLaiXe();
                    pause();
                    break;
                case 3:
                    themTuyen();
                    pause();
                    break;
                case 4:
                    hienThiTuyen();
                    pause();
                    break;
                case 5:
                    nhapBangPhanCong();
                    pause();
                    break;
                case 6:
                    hienThiBangPhanCong();
                    pause();
                    break;
                case 7:
                    sapXepTheoTen();
                    pause();
                    break;
                case 8:
                    sapXepTheoSoLuongTuyen();
                    pause();
                    break;
                case 9:
                    tongKeKhoangCach();
                    pause();
                    break;
                case 10:
                    luuDanhSachLaiXe();
                    luuDanhSachTuyen();
                    luuDanhSachBangPhanCong();
                    pause();
                    break;
                case 0:
                    System.out.println("Thoátttttt...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng thử lại.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void themLaiXe(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập họ tên:");
        String hoTen = sc.nextLine();
        System.out.print("Nhập địa chỉ:");
        String diaChi = sc.nextLine();
        System.out.print("Nhập số điện thoại:");
        String SDT = sc.nextLine();
        System.out.print("Nhập trình độ (A-F):");
        LaiXe.TrinhDo trinhDo = LaiXe.TrinhDo.valueOf(sc.nextLine().toUpperCase());
        LaiXe laiXe = new LaiXe(hoTen,diaChi,SDT,trinhDo);
        laiXes.add(laiXe);
    }

    private static void hienThiLaiXe(){
        System.out.println("Danh sách lái xe:");
        laiXes.forEach(System.out::println);
    }

    private static void themTuyen(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập khoảng cách:");
        int khoangCach =  sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập số điểm dừng:");
        int soDiemDung = sc.nextInt();
        sc.nextLine();
        Tuyen tuyen = new Tuyen(khoangCach,soDiemDung);
        tuyens.add(tuyen);

    }

    private static void hienThiTuyen(){
        System.out.println("Danh sách tuyến:");
        tuyens.forEach(System.out::println);
    }

    private static void nhapBangPhanCong(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào mã lái xe:");
        int idLaiXe = sc.nextInt();
        sc.nextLine();
        LaiXe laiXe = timKiemLaiXe(idLaiXe);
        if(laiXe==null){
            System.out.println("Không tìm thấy người lái xe!");
            return;
        }


        System.out.print("Nhập vào mã tuyến đường:");
        int idTuyen = sc.nextInt();
        sc.nextLine();
        Tuyen tuyen = timKiemTuyen(idTuyen);
        if(tuyen==null){
            System.out.println("Không tìm thấy tuyến đường!");
            return;
        }
        boolean check = bangPhanCongs.stream().anyMatch(b-> b.getTuyen().equals(tuyen) && b.getLaiXe().equals(laiXe));
        if(check == true){
            System.out.println("Người lái xe đã được phân công cho tuyến đường này!");
            return;
        }
        System.out.print("Nhập vào số lượt lái:");
        int soLuot = sc.nextInt();
        sc.nextLine();

        int totals = bangPhanCongs.stream().filter(b->b.getLaiXe().equals(laiXe)).mapToInt(BangPhanCong::getSoLuot).sum();
        if(totals+soLuot>15){
            System.out.println("Tổng số lượt lái xe đã vượt quá số quy định trong ngày (15) !");
            return;
        }

        Map<Integer,String > tongLuotLai = new HashMap<Integer,String>();

        BangPhanCong bangPhanCong = new BangPhanCong(laiXe,tuyen,soLuot);
        bangPhanCongs.add(bangPhanCong);

    }

    private static void hienThiBangPhanCong(){
        System.out.println("Danh sách bảng phân công:");
        bangPhanCongs.forEach(System.out::println);
    }


    private static LaiXe timKiemLaiXe(int findId){
        return laiXes.stream().filter(l->l.getMaLX()==findId).findFirst().orElse(null);
    }

    private static Tuyen timKiemTuyen(int findId) {
        return tuyens.stream().filter(t-> t.getMaTuyen()==findId).findFirst().orElse(null);
    }

    private static void sapXepTheoTen(){
        bangPhanCongs.sort(Comparator.comparing(l->l.getLaiXe().getHoTen()));
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo tên là:");
        hienThiBangPhanCong();
    }

    private static void sapXepTheoSoLuongTuyen(){
        System.out.println("Danh sách bảng phân công sau khi sắp xếp theo số lượng tuyến là:");
        Map<LaiXe,Long> soLuongTuyenDuong  = bangPhanCongs.stream().collect(Collectors.groupingBy(BangPhanCong::getLaiXe,Collectors.counting()));
        System.out.print("Số lượng tuyến đường"+soLuongTuyenDuong);
        bangPhanCongs.sort((a1, a2) -> Long.compare(soLuongTuyenDuong.get(a2.getLaiXe()), soLuongTuyenDuong.get(a1.getLaiXe())));
        hienThiBangPhanCong();
    }

    private static void tongKeKhoangCach(){
        System.out.println("Bảng tổng kê khoảng cách chạy trong ngày:");
        Map<LaiXe,Integer>  tongKhoangCach = bangPhanCongs.stream().collect(Collectors.groupingBy(BangPhanCong::getLaiXe,Collectors.summingInt(a-> (int) (a.getTuyen().getKhoangCach()*a.getSoLuot()))));
        tongKhoangCach.forEach((laiXes,sum)->{
            System.out.println("Tài xế:"+laiXes.getHoTen()+" -Tổng quãng đường:"+sum+" km");
        });
    }

    private static void luuDanhSachLaiXe(){
        try (FileOutputStream fos = new FileOutputStream("laixe.txt");
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {

            laiXes.forEach(laiXe -> {
                try{
                    String thongTinLaiXe = laiXe.toString();
                    bw.write(thongTinLaiXe);
                    bw.newLine();

                }catch (IOException e){
                    System.out.println("Lỗi khi lưu thông tin lái xe: " + e.getMessage());
                }
            });
            System.out.println("Danh sách lái xe đã được lưu vào file.");

        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách lái xe: " + e.getMessage());
        }
    }

    private static void luuDanhSachTuyen(){
        try (FileOutputStream fos = new FileOutputStream("tuyen.txt");
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {

            tuyens.forEach(tuyen -> {
                try{
                    String thongTinTuyen = tuyen.toString();
                    bw.write(thongTinTuyen);
                    bw.newLine();

                }catch (IOException e){
                    System.out.println("Lỗi khi lưu thông tin tuyến: " + e.getMessage());
                }
            });
            System.out.println("Danh sách tuyến đã được lưu vào file.");

        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách tuyến: " + e.getMessage());
        }
    }

    private static void luuDanhSachBangPhanCong(){
        try (FileOutputStream fos = new FileOutputStream("bangphancong.txt");
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {

            bangPhanCongs.forEach(bangphancong -> {
                try{
                    String thongTinBangPhanCong = bangphancong.toString();
                    bw.write(thongTinBangPhanCong);
                    bw.newLine();

                }catch (IOException e){
                    System.out.println("Lỗi khi lưu thông tin bảng phân công: " + e.getMessage());
                }
            });
            System.out.println("Danh sách bảng phân công đã được lưu vào file.");

        } catch (IOException e) {
            System.out.println("Lỗi khi lưu danh sách bảng phân công: " + e.getMessage());
        }
    }

    public static void pause() {
        System.out.println("Nhấn phím Enter để tiếp tục...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

public class LaiXe {
    enum TrinhDo{
        A,B,C,D,E,F;
    }

    private static int nextId=10000;
    private int maLX;
    private String hoTen;
    private String diaChi;
    private String SDT;
    private TrinhDo trinhDo;

    public LaiXe(String hoTen, String diaChi, String SDT, TrinhDo trinhDo) {
        this.maLX=nextId++;
        this.hoTen=hoTen;
        this.diaChi=diaChi;
        this.SDT=SDT;
        this.trinhDo=trinhDo;
    }

    public int getMaLX() {
        return maLX;
    }

    public void setMaLX(int maLX) {
        this.maLX = maLX;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public TrinhDo getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(TrinhDo trinhDo) {
        this.trinhDo = trinhDo;
    }

    @Override
    public String toString() {
        return "Mã LX:" + maLX+" -Họ tên:"+hoTen+" -Địa chỉ:"+diaChi+" -SDT:"+SDT+" -Trình độ:"+trinhDo;
    }
}

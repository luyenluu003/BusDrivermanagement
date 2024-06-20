public class Tuyen {
    private static int nextId=100;
    private int maTuyen;
    private int khoangCach;
    private int soDiemDung;

    public Tuyen(int khoangCach,int soDiemDung){
        this.maTuyen=nextId++;
        this.khoangCach=khoangCach;
        this.soDiemDung= soDiemDung;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public float getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    public int getSoDiemDung() {
        return soDiemDung;
    }

    public void setSoDiemDung(int soDiemDung) {
        this.soDiemDung = soDiemDung;
    }

    @Override
    public String toString() {
        return "Mã tuyến:"+maTuyen+" -Khoảng cách:"+khoangCach+" -Số điểm dừng:"+soDiemDung;
    }
}

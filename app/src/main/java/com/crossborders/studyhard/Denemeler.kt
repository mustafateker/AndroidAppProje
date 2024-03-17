import java.util.Date

class Denemeler {


    var denemeAdi: String = ""
    var denemetarih: Date? = null // Nullable olarak tanımlandı

    var denemeNet: Double = 0.0

    constructor( denemeAdi: String, denemetarih: Date?, denemeNet: Double) {
        this.denemeAdi = denemeAdi
        this.denemetarih = denemetarih
        this.denemeNet = denemeNet
    }
}

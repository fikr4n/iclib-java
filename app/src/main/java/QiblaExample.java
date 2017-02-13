import net.alqs.iclib.qibla.Dms;
import net.alqs.iclib.qibla.Qibla;


public class QiblaExample extends Example {
	
	private String _(Dms dms) {
		return String.format("{%d, %d, %f}", dms.degree, dms.minute, dms.second);
	}
	
	public void istiqlal() {
		double lat = -6.169777778;
		double lng = 106.8307333;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
	
	public void yogyakarta() {
		double lat = -(7 + 48 / 60.0);
		double lng = 110 + 21 / 60.0;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
		
	public void nearnorthpole() {
		double lat = 89;
		double lng = 39.82616111;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
	
	public void nearsouthpole() {
		double lat = -89;
		double lng = 39.82616111;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
	
	public void makkah_east() {
		double lat = 21.42250833;
		double lng = 40;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
	
	public void makkah_west() {
		double lat = 21.42250833;
		double lng = 39;
		print(Qibla.findDirection(lat, lng));
		print(Qibla.findDirectionDms(lat, lng));
		print(_(Qibla.findDirectionDms(lat, lng)));
	}
	
	public static void main(String[] args) throws Exception {
		new QiblaExample().run();
	}
}

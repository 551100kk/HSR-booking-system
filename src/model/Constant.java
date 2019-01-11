package model;

public class Constant {
	public static final String[] stationChineseName = {
		"南港", "台北", "板橋", "桃園", "新竹","苗栗",
		"台中", "彰化", "雲林",	"嘉義", "台南","左營"
	};
	
	public static final String[] stationEnglishName = {
		"Nangang", "Taipei", "Banciao", "Taoyuan", "Hsinchu", "Miaoli",
		"Taichung", "Changhua", "Yunlin", "Chiayi", "Tainan", "Zuoying"
	};
	
	public static final int standardPrice[][] = {
			{},
			{40},
			{70, 40},
			{200, 160, 130},
			{330, 290, 260, 130},
			{480, 430, 400, 280, 140},
			{750, 700, 670, 540, 410, 270},
			{870, 820, 790, 670, 540, 390, 130},
			{970, 930, 900, 780, 640, 500, 230, 110},
			{1120, 1080, 1050, 920, 790, 640, 380, 250, 150},
			{1390, 1350, 1320, 1190, 1060, 920, 650, 530, 420, 280},
			{1530, 1490, 1460, 1330, 1200, 1060, 790, 670, 560, 410, 140},
	};
	
	public static final int businessPrice[][] = {
			{},
			{260},
			{310, 260},
			{500, 440, 400},
			{700, 640, 590, 400},
			{920, 850, 800, 620, 410},
			{1330, 1250, 1210, 1010, 820, 610},
			{1510, 1430, 1390, 1210, 1010, 790, 400},
			{1660, 1600, 1550, 1370, 1160, 950, 550, 370},
			{1880, 1820, 1780, 1580, 1390, 1160, 770, 580, 430},
			{2290, 2230, 2180, 1990, 1790, 1580, 1180, 1000, 830, 620},
			{2500, 2440, 2390, 2200, 2000, 1790, 1390, 1210, 1040, 820, 410},
	};
	
	public static final String[] ticketType = {
		"標準", "學生", "愛心/孩童/敬老", "商務"
	};
	
	public static final String[] seatType = {
		"隨機", "靠窗", "靠走道"
	};
	
	public static final String[] WEEKDAYS = {
		"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
	};
	
	public static final int earlyBirdDays = 3;
	public static final int bookingDays = 28;
	public static final int totalSeat = 9 * 18 * 5;
}

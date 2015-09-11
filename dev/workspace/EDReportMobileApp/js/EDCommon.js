var _COMMON = {};

/** -------------- Constant ---->>>>----------- **/
 
_COMMON.HOST =  "json/";
_COMMON.URL = {
		ROO1 : "R001.json",
		ROO2 : "ROO2.json",
		ROO3 : "ROO3.json",
		ROO5 : "ROO5.json",
		ROO4 : "ROO4.json",
		ROO6 : "ROO6.json",
		ROO7 : "ROO7.json",
		ROO8 : "ROO8.json",
		ROO9 : "ROO9.json",
		RO10 : "RO10.json",
		RO11 : "RO11.json",
		RO12 : "RO12.json",
		RO13 : "RO13.json",
		RO14 : "RO14.json",
		RO15 : "RO15.json",
		RO16 : "RO16.json",
		RO17 : "RO17.json",
		RO18 : "RO18.json",
		RO0X : "RO0X.json",
}



/** ----------<<<  ---- Constant----------------------- **/

function parseNum(val){  
	val = val.replace(/[^\d\.\-eE+]/g, "");
	return ($.trim(val) == '')? 0 : parseFloat(val);
}

function formatNum(digits,val){
	return val.toFixed(digits).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function checkNum(val){
	return ($.trim(val) == '')? '-' : val;
}

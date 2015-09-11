var _COMMON = {};

/** -------------- Constant ---->>>>----------- **/
 
_COMMON.HOST =  "json/";
_COMMON.URL = {
		R001 : "R001.json",
		R002 : "R002.json",
		R003 : "R003.json",
		R004 : "ROO4.json",
		R005 : "R005.json",
		R006 : "R006.json",
		R007 : "ROO7.json",
		R008 : "R008.json",
		R009 : "R009.json",
		R010 : "R010.json",
		R011 : "R011.json",
		R012 : "R012.json",
		R013 : "R013.json",
		R014 : "R014.json",
		R015 : "R015.json",
		R016 : "R016.json",
		R017 : "R017.json",
		R018 : "R018.json",
		R00X : "R00X.json",
}



/** ----------<<<  ---- Constant----------------------- **/

function parseNum(val){  
	//val = val.replace(/[^\d\.\-eE+]/g, "");
	return ($.trim(val) == '')? 0 : parseFloat(val);
}

function formatNum(digits,val){
	return val.toFixed(digits).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function checkNum(val){
	return ($.trim(val) == '')? '-' : val;
}

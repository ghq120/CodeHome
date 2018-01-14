  var clo;
  var dot;
  function clock(){
    var a = new Date();
    
    year0 = parseInt(a.getFullYear()/1000);
    year1 = parseInt(a.getFullYear()/100)%10;
    year2 = parseInt(a.getFullYear()/10)%10;
    year3 = a.getFullYear()%10;
    var year = [year0,year1,year2,year3];

    month0 = parseInt((a.getMonth()+1)/10);
    month1 = (a.getMonth()+1)%10;
    
    day0 = parseInt(a.getDate()/10);
    day1 = a.getDate()%10;
    
    week = a.getDay();
         
    hour0 = parseInt(a.getHours()/10);//hour
    hour1 = a.getHours()%10;
    min0 = parseInt(a.getMinutes()/10);//min
    min1 = a.getMinutes()%10;
    sec0 = parseInt(a.getSeconds()/10);//sec
    sec1 = a.getSeconds()%10;
    if(sec1%2 ==0) {
      dot = 1;
    }else{
      dot = -1;
    }
    //year
    for (var i = 0; i < year.length; i++) {
        document.getElementsByName('year')[i].src="date/"+year[i]+".png";
    };
    //month
    document.getElementsByName('month')[0].src="date/"+month0+".png";
    document.getElementsByName('month')[1].src="date/"+month1+".png";
    //date
    document.getElementsByName('day')[0].src="date/"+day0+".png";
    document.getElementsByName('day')[1].src="date/"+day1+".png";
    //week
    document.getElementsByName('week')[0].src="week/"+week+".png";
    //time
    document.getElementsByName('hour')[0].src="time/"+hour0+".png";
    document.getElementsByName('hour')[1].src="time/"+hour1+".png";
    document.getElementsByName('dot')[0].src="time/p"+dot+".png";
    document.getElementsByName('minute')[0].src="time/"+min0+".png";
    document.getElementsByName('minute')[1].src="time/"+min1+".png";
    document.getElementsByName('dot')[1].src="time/p"+dot+".png";
    document.getElementsByName('second')[0].src="time/"+sec0+".png";
    document.getElementsByName('second')[1].src="time/"+sec1+".png";
  }
  document.getElementsByName('img')[0].onclick=function(){
    clo=setInterval('clock()',1000);
  }
  document.getElementsByName('img')[1].onclick=function(){
    clearInterval(clo);
  }
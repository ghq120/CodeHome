    //每个方块的数组
    var a1 = [[1,1],[1,1]];                                         
    var b1 = [[2],[2],[2],[2]];
    var b2 = [[2,2,2,2]];
    var c1 = [[3,0],[3,3],[0,3]];
    var c2 = [[0,3,3],[3,3,0]];
    var d1 = [[0,4],[4,4],[4,0]];                                               
    var d2 = [[4,4,0],[0,4,4]];                                                 
    var e1 = [[5,0],[5,0],[5,5]];
    var e2 = [[5,5,5],[5,0,0]];
    var e3 = [[5,5],[0,5],[0,5]];
    var e4 = [[0,0,5],[5,5,5]];
    var f1 = [[0,6],[0,6],[6,6]];
    var f2 = [[6,0,0],[6,6,6]];
    var f3 = [[6,6],[6,0],[6,0]];
    var f4 = [[6,6,6],[0,0,6]];
    var g1 = [[0,7,0],[7,7,7]];
    var g2 = [[7,0],[7,7],[7,0]];
    var g3 = [[7,7,7],[0,7,0]];
    var g4 = [[0,7],[7,7],[0,7]];

    //每个方块的长和宽
    var h1 = [2,2];

    var i1 = [1,4];
    var i2 = [4,1];
    
    var j1 = [2,3];
    var j2 = [3,2];
    
    var k1 = [2,3];
    var k2 = [3,2];
    
    var l1 = [2,3];
    var l2 = [3,2];
    var l3 = [2,3];
    var l4 = [3,2];
    
    var m1 = [2,3];
    var m2 = [3,2];
    var m3 = [2,3];
    var m4 = [3,2];
    
    var n1 = [3,2];
    var n2 = [2,3];
    var n3 = [3,2];
    var n4 = [2,3];

    //     0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18
    var x=[a1,b1,b2,c1,c2,d1,d2,e1,e2,e3,e4,f1,f2,f3,f4,g1,g2,g3,g4];
    var y=[h1,i1,i2,j1,j2,k1,k2,l1,l2,l3,l4,m1,m2,m3,m4,n1,n2,n3,n4];

    //顺时针旋转
    var p = [0,2,1,4,3,6,5,8,9,10,7,12,13,14,11,16,17,18,15];
    //逆时针旋转
    var q = [0,2,1,4,3,6,5,10,7,8,9,14,11,12,13,18,15,16,17];
    
    
    var ran=Math.round(Math.random()*18);
    var time = 1000;
    var nextran = 0

    
    nextran = Math.round(Math.random()*18);
       

    

    //4×4数组赋值打印
    function odaying(){  
        for (var i = 0; i < y[nextran][1]; i++) {
            for (var j = 0; j < y[nextran][0]; j++) { 
                if(x[nextran][i][j] !=0 ){
                    o[i][j] = x[nextran][i][j];
                }
            }
        }
    
        for(var i = 0; i<4; i++){
            for(var j = 0; j<4; j++){
                if(o[i][j]!=0){
                    document.getElementsByName("block")[i*4+j].src="block/"+String(o[i][j])+".jpg";
                }
            }
        }
        
         
        
    }



    
    var lr = 0; //左右移动增加量
    var dd = 0; //下移增加量
    
    //创建4×4二维数组并初始化
    var o = new Array(4);
    for (var j = 0; j < 4; j++) {
        o[j] = new Array(4);
    }

    for (var i = 0; i < 4; i++) {
        for (var j = 0; j < 4; j++) {
            o[i][j]=0
        }
    }

    //创建18×10二维数组并初始化
    var oo = new Array(18);
    for (var j = 0; j < 18; j++) {
        oo[j] = new Array(10);
    }

    for (var i = 0; i < 18; i++) {
        for (var j = 0; j < 10; j++) {
            oo[i][j]=0;
        }
    }

    
    //18×10数组赋值
    function oofuzhi(){
        for (var i = 0; i <  y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
                if(x[ran][i][j] !=0 ){
                oo[i+dd][j+4+lr] = x[ran][i][j]
            }
            }
        }
    }

    //18×10区域进行贴图
    function oodaying(){
        for(var i = 0; i<18; i++){
            for(var j = 0; j<10; j++){
                document.getElementsByName("block1")[i*10+j].src="block/"+String(oo[i][j])+".jpg";
            }
        }
    }

    function piant(){
        oofuzhi();
        
        oodaying();
    }
    
    
    
    //将18×10的相关位置清空
    function cla(){
        for (var i = 0; i < y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
               if (x[ran][i][j] !=0) {
                oo[i+dd][j+4+lr] = 0;
               }
            }
        }
    }

    //旋转碰撞判定
    function judgeclockwise(){
        cla();
        for (var i = 0; i < y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
                if(x[ran][i][j]>0){
                    if(oo[p[ran]][i+dd][j+4+lr] > 0){
                        piant();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //顺时针旋转
    function clockwise(){       
        cla();
        if (y[ran][1]+dd <= 17) {
            ran=p[ran];
        }
        if(y[ran][0]+lr+4 > 10){
            lr = lr-1;
            ran=p[ran];
        }
        piant();
    }

    /*//逆时针旋转
    function anticlockwise(){
        ran=q[ran];
        daying();
    }*/

    //下降碰撞判定
    function judgedown(){
        cla();
        for (var i = 0; i < y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
                if(x[ran][i][j]>0){
                    if(oo[i+dd+1][j+4+lr] > 0){
                        piant();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //下降
    function down(){
        
        if (y[ran][1]+dd < 18 && judgedown()) {      
            dd++;
            piant();
        } else{ 
          clearrow(); 
          dd = 0;
          lr = 0;   
          ran = Math.round(Math.random()*18);
          //ran = nextran
          piant();
        } 

        
    }


  
    //左移碰撞判定
    function judgeleft(){
        cla();
        for (var i = 0; i < y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
                if(x[ran][i][j]>0){
                    if(oo[i+dd][j+4+lr-1] > 0){
                        piant();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //左移
    function left(){      
        if(lr+4>0 && judgeleft()){
            cla();
            lr--;
        }
        piant();
    }

    //右移碰撞判定
    function judgeright(){
        cla();
        for (var i = 0; i < y[ran][1]; i++) {
            for (var j = 0; j < y[ran][0]; j++) {
                if(x[ran][i][j]>0){
                    if(oo[i+dd][j+4+lr+1] > 0){
                        piant();
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //右移
    function right(){
        if(y[ran][0]+lr<6 && judgeright()){
            cla();
            lr++;
        }
        piant();
    }

    //消除行方法
    
    var num =0;
    var score = 0;
    function clearrow (){
      for (var i = 0; i < 18; i++) {
        for (var j = 0; j < 10; j++) {
          if(oo[i][j] != 0){
            num++;
          }
          if(num == 10){
            score += 10;
            for (var k = i; k >0; k--) {
              for(var l = 0; l <10; l++){
                oo[k][l] = oo[k-1][l] 
              }
            }
          }
        }
        num = 0;
      }
      fenshu(score);
      level();
    }

    //计算分数
    function fenshu(score){
      document.getElementsByName('score')[0].value = score;
    }

    //游戏结束
    function gameover(){
      for (var i = 0; i < 10; i++) {
        if (oo[0][i] != 0) {
          alert("GameOver!!");
          location.reload();
        } 
      }
    }

    //按键触发操作
    document.getElementsByTagName('body')[0].onkeydown=function(e){
        //alert(e.keyCode);
        switch(e.keyCode){
            case 38://上键
                clockwise();
                break;
            case 40://下键
                down();
                break;
            case 37://左键
                left();
                break;      
            case 39://右键                
                right();
                break;
            case 32://空格
                
                break;       
        }
    }

    
    
    //自动下落
    document.getElementsByName('start')[0].onclick=function(){
      piant();
      clo=setInterval('down()',time);
    }
    document.getElementsByName('pause')[0].onclick=function(){
      clearInterval(clo);
    }
    
    //增加难度
    function level(){
        clearInterval(clo);
        if(score>=0 && score<=100){
          time = 1000;
        }else if(score>100 && score<=200){
            time = 700;
        }else if(score>200 && score<=300){
            time = 400;
        }else{
            time = 200;
        }
        clo=setInterval('down()',time);  
    }
    
    

    
    


    

   

    
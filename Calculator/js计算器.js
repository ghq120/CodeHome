        var sym,temp,symbol;
        var temp2 = 0,temp3 = 0;
        var isClickOper=false;  
        var isClickPoint=false;
//角度、弧度
document.getElementsByName('du')[0].onfocus=function(){
    document.getElementById('scn').value=String(Number(document.getElementById('scn').value)/Math.PI*180)   
}
document.getElementsByName('du')[1].onfocus=function(){
    document.getElementById('scn').value=String(Number(document.getElementById('scn').value)*Math.PI/180)
}
//backspace
document.getElementsByName('back')[0].onclick=function(){
    document.getElementById('scn').value=document.getElementById('scn').value.substr(0,(document.getElementById('scn').value).length-1)
}
//CE
document.getElementsByName('ce')[0].onclick=function(){
    document.getElementById('scn').value='0'
    temp3=0;
}
//In
document.getElementsByName('In')[0].onclick=function(){
    document.getElementById('scn').value=Math.log(Number(document.getElementById('scn').value))
}
//sin
document.getElementsByName('sin')[0].onclick=function(){
    document.getElementById('scn').value=String(Math.sin(Number(document.getElementById('scn').value)*Math.PI/180))
}
//cos
document.getElementsByName('cos')[0].onclick=function(){
    document.getElementById('scn').value=String(Math.cos(Number(document.getElementById('scn').value)*Math.PI/180))
}
//tan
document.getElementsByName('tan')[0].onclick=function(){
    document.getElementById('scn').value=String(Math.sin(Number(document.getElementById('scn').value)*Math.PI/180))
}

//x^3
document.getElementsByName('x^3')[0].onclick=function(){
    document.getElementById('scn').value=Math.pow(document.getElementById('scn').value,3)
}
//x^2
document.getElementsByName('x^2')[0].onclick=function(){
    document.getElementById('scn').value=Math.pow(document.getElementById('scn').value,2)
}
//1/x
document.getElementsByName('1/x')[0].onclick=function(){
    document.getElementById('scn').value=String(Number(1/document.getElementById('scn').value))
}
//PI
document.getElementsByName('pi')[0].onclick=function(){
    document.getElementById('scn').value=Math.PI
}
//阶乘
document.getElementsByName('jc')[0].onclick=jc;

//+/-
document.getElementsByName('+/-')[0].onclick=function(){
    document.getElementById('scn').value=document.getElementById('scn').value*(-1)
}
//.
document.getElementsByName('.')[0].onclick=function(){
    document.getElementById('scn').value+='.'
}

//括号
document.getElementsByName('kh')[0].onclick=function(){
    document.getElementById('scn').value+='('
}
document.getElementsByName('kh')[1].onclick=function(){
    document.getElementById('scn').value+=')'
}
//数字

document.getElementsByName('1')[0].onclick=function(){
    Input_num('1');
}
document.getElementsByName('2')[0].onclick=function(){
    Input_num('2');
}
document.getElementsByName('3')[0].onclick=function(){
    Input_num('3');
}
document.getElementsByName('4')[0].onclick=function(){
    Input_num('4');
}
document.getElementsByName('5')[0].onclick=function(){
    Input_num('5');
}
document.getElementsByName('6')[0].onclick=function(){
    Input_num('6');
}
document.getElementsByName('7')[0].onclick=function(){
    Input_num('7');
}
document.getElementsByName('8')[0].onclick=function(){
    Input_num('8');
}
document.getElementsByName('9')[0].onclick=function(){
    Input_num('9');
}
document.getElementsByName('0')[0].onclick=function(){
    Input_num('0');
}
//运算符号
document.getElementsByName('/')[0].onclick=function(){
    Input_symb('/')
}
document.getElementsByName('mod')[0].onclick=function(){
    Input_symb('Mod')
}
document.getElementsByName('*')[0].onclick=function(){
    Input_symb('*')
}
document.getElementsByName('x^y')[0].onclick=function(){
    Input_symb('x^y')
}
document.getElementsByName('-')[0].onclick=function(){
    Input_symb('-')
}
document.getElementsByName('+')[0].onclick=function(){
    Input_symb('+')
}

//=
document.getElementsByName('=')[0].onclick=cacul;

    //退格按钮
    function back() {
      var name=document.getElementById('scn');
      var temp=name.value;
      name.value="";
      for(var i=0;i<temp.length-1;i++)
        name.value+=temp[i];
    }
    
    //判断输入之前的数字是否为0，如果是则替换掉0，如果不是在上一个数字后面进行追加
    function Input_num(num){
            if(document.getElementById('scn').value=='0'||isClickOper){
                document.getElementById('scn').value=num;
                isClickOper=false
            }else if(document.getElementById('scn').value.length<32){
                document.getElementById('scn').value+=num;
            }
        } 
    //输完数字以后，将屏幕上的数字存在一个临时变量中，将屏幕清空
    /*function Input_symb(sym){
        document.getElementById('scn').value='0';
        symbol=sym;
        queal();
    }*/
    function cacul(){
        queal();    
        temp3=0;
    }

    function Input_symb(sym){
      symbol=sym;
      isClickOper=true;
      queal();
    }

    //判断输入的符号作出对应的运算
    function queal(){
        temp=Number(document.getElementById('scn').value);
        if(temp3!=0){
            switch(symbol){
                case '+': temp2=temp3+temp;     
                break;
                case '-': temp2=temp3-temp;
                break;
                case '*': temp2=temp3*temp;
                break;
                case '/': temp2=temp3/temp;
                break;
                case 'x^y': temp2 = Math.pow(temp3,temp);
                break;
                case 'Mod': temp2 = temp3%temp;
                break;
            }
        }else{
                temp2=temp;
            }
            temp=String(temp2);
            document.getElementById('scn').value=temp;
            temp3=temp2 ;
        }
    
    //n!的方法
    function jc (){
        var a =document.getElementById('scn').value;
        var sum = 1;
        if (a<0) {
            document.getElementById('scn').value = "函数输入无效";
        }else if(a==0){
            document.getElementById('scn').value = 1;
        } else{
                for (var i=1;i<=a;i++) {
                    sum*=i; 
                }
            document.getElementById('scn').value = sum;
            }
    }
$(function(){
    var sym,temp,symbol;
    var temp2 = 0,temp3 = 0;
    var isClickOper=false;  
    var isClickPoint=false;
//角度、弧度
    $('#jdu').focus(function(){
        $('#scn').val($('#scn').val()/Math.PI*180)
    })

    $('#hdu').focus(function(){
        $('#scn').val($('#scn').val()*Math.PI/180)
    })

//backspace
    $('#back').click(function(){
        $('#scn').val($('#scn').val().substr(0,$('#scn').val().length-1))
    })

//CE
    $('#ce').click(function(){
        $('#scn').val('0');
    })
    temp3=0;

//In
    $('#In').click(function(){
        $('#scn').val(Math.log($('#scn').val()))
    })

//sin
    $('#sin').click(function(){
        $('#scn').val(Math.sin($('#scn').val()*Math.PI/180))
    })

//cos
    $('#cos').click(function(){
        $('#scn').val(Math.cos($('#scn').val()*Math.PI/180))
    })

//tan
    $('#tan').click(function(){
        $('#scn').val(Math.tan($('#scn').val()*Math.PI/180))
    })

//x^3
    $('#x3').click(function(){
        $('#scn').val(Math.pow($('#scn').val(),3))
    })

//x^2
    $('#x2').click(function(){
        $('#scn').val(Math.pow($('#scn').val(),2))
    })

//1/x
    $('#1x').click(function(){
        $('#scn').val(1/$('#scn').val())
    })

//PI
    $('#pi').click(function(){
        $('#scn').val(Math.PI)
    })

//阶乘
    $('#jc').click(function(){
        jc()
    })

//+/-
    $('#qufan').click(function(){
        $('#scn').val($('#scn').val*(-1))
    })

//.
    $('#point').click(function(){
        $('#scn').val($('#scn').val()+'.')
    })

//括号

    $('#lkh').click(function(){
        $('#scn').val($('#scn').val()+'(')
    })

    $('#rkh').click(function(){
        $('#scn').val($('#scn').val()+')')
    })

//数字
    $('#1').click(function(){
        Input_num('1');
    })

    $('#2').click(function(){
        Input_num('2');
    })

    $('#3').click(function(){
        Input_num('3');
    })

    $('#4').click(function(){
        Input_num('4');
    })

    $('#5').click(function(){
        Input_num('5');
    })

    $('#6').click(function(){
        Input_num('6');
    })

    $('#7').click(function(){
        Input_num('7');
    })

    $('#8').click(function(){
        Input_num('8');
    })

    $('#9').click(function(){
        Input_num('9');
    })

    $('#0').click(function(){
        Input_num('0');
    })

//运算符号
    $('#chu').click(function(){
        Input_symb('/');
    })

    $('#mod').click(function(){
        Input_symb('mod');
    })

    $('#cheng').click(function(){
        Input_symb('*');
    })

    $('#xy').click(function(){
        Input_symb('x^y');
    })

    $('#jian').click(function(){
        Input_symb('-');
    })

    $('#jia').click(function(){
        Input_symb('+');
    })

//=
    $('#dengyu').click(function(){
        cacul();
    })

    //判断输入之前的数字是否为0，如果是则替换掉0，如果不是在上一个数字后面进行追加
    function Input_num(num){
            if($('#scn').val()=='0'||isClickOper){
                $('#scn').val(num);
                isClickOper=false
            }else if($('#scn').val().length<32){
                $('#scn').val($('#scn').val()+num)
            }
    }
    
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
        temp = ($('#scn').val());
        if(temp3!=0){
            switch(symbol){
                case '+': temp2=temp3*1+temp*1;     
                break;
                case '-': temp2=temp3-temp;
                break;
                case '*': temp2=temp3*temp;
                break;
                case '/': temp2=temp3/temp;
                break;
                case 'x^y': temp2 = Math.pow(temp3,temp);
                break;
                case 'mod': temp2 = temp3%temp;
                break;
            }
        }else{
            temp2=temp;
         }
            temp=temp2;
            $('#scn').val(temp);
            temp3=temp2 ;
    }
   
    //n!的方法
    function jc (){
        var a = $('#scn').val();
        var sum = 1;
        if (a<0) {
            $('#scn').val('函数输入无效');
        }else if(a==0){
            $('#scn').val(1);
        }else{
            for (var i=1;i<=a;i++) {
                sum*=i; 
            }
            $('#scn').val(sum);
        }
    }
    
})


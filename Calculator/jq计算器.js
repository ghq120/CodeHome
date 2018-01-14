        

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
$(function(){
    $('#x3').click(function(){
        $('#scn').val(Math.pow($('#scn').val(),3))
    })
})

//x^2
$(function(){
    $('#x2').click(function(){
        $('#scn').val(Math.pow($('#scn').val(),2))
    })
})

//1/x
$(function(){
    $('#1x').click(function(){
        $('#scn').val(1/$('#scn').val())
    })
})

//PI
$(function(){
    $('#pi').click(function(){
        $('#scn').val(Math.PI)
    })
})

//阶乘
$(function(){
    $('#jc').click(function(){
        jc()
    })
})

//+/-
$(function(){
    $('#qufan').click(function(){
        $('#scn').val($('#scn').val*(-1))
    })
})

//.
$(function(){
    $('#point').click(function(){
        $('#scn').val($('#scn').val()+'.')
    })

//括号
$(function(){
    $('#lkh').click(function(){
        $('#scn').val($('#scn').val()+'(')
    })
})

$(function(){
    $('#rkh').click(function(){
        $('#scn').val($('#scn').val()+')')
    })
})

//数字

$(function(){
    $('#1').click(function(){
        Input_num('1');
    })
})

$(function(){
    $('#2').click(function(){
        Input_num('2');
    })
})

$(function(){
    $('#3').click(function(){
        Input_num('3');
    })
})

$(function(){
    $('#4').click(function(){
        Input_num('4');
    })
})

$(function(){
    $('#5').click(function(){
        Input_num('5');
    })
})

$(function(){
    $('#6').click(function(){
        Input_num('6');
    })
})

$(function(){
    $('#7').click(function(){
        Input_num('7');
    })
})

$(function(){
    $('#8').click(function(){
        Input_num('8');
    })
})

$(function(){
    $('#9').click(function(){
        Input_num('9');
    })
})

$(function(){
    $('#0').click(function(){
        Input_num('0');
    })
})

//运算符号
$(function(){
    $('#chu').click(function(){
        Input_symb('/')
    })
})

$(function(){
    $('#mod').click(function(){
        Input_symb('mod')
    })
})

$(function(){
    $('#cheng').click(function(){
        Input_symb('*')
    })
})

$(function(){
    $('#xy').click(function(){
        Input_symb('x^y')
    })
})

$(function(){
    $('#jian').click(function(){
        Input_symb('-')
    })
})

$(function(){
    $('#jia').click(function(){
        Input_symb('+')
    })
})

//=
$(function(){
    $('#dengyu').click(function(){
        cacul();
    })
})

    //退格按钮
    /*function back() {
      var name=document.getElementById('scn');
      var temp=name.value;
      name.value="";
      for(var i=0;i<temp.length-1;i++)
        name.value+=temp[i];
    }
    */
    //判断输入之前的数字是否为0，如果是则替换掉0，如果不是在上一个数字后面进行追加
    function Input_num(num){
            if($('#scn').val()=='0'||isClickOper){
                $('#scn').val(num);
                isClickOper=false
            }else if($('#scn').val().length<32){
                $('#scn').val($('#scn').val()+num)
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
        temp=$('#scn').val();
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
                case 'x^y': $('#scn').val(Math.pow(temp,$('#scn').val()))
                break;
                case 'Mod': $('#scn').val(temp%$('#scn').val())
                break;
            }
        }else{
            temp2=temp;
         }
            temp=String(temp2);
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

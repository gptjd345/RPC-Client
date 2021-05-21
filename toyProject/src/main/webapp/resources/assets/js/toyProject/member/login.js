
//id 입력 확인 함수
var idValidate = function()
{
	var userId = $('#userid').val().trim();
	
	if(userId.length == 0)
	{	 //뒤에 span 태그는 하나만 있어야함 
        $('#userid').nextAll("*").remove();
        $('#userid').after('<span class="fail"> 아이디를 입력해주세요. </span>');
		
        return false;
	}	
	else
	{	
		$("#userid").nextAll("*").remove();
		return true;
	}	
};


//pw 입력 확인 함수
var pwValidate = function()
{
	var passWord = $('#password').val().trim();
	
	if(passWord.length == 0)
	{
		 //뒤에 span 태그는 하나만 있어야함 
        $('#password').nextAll("*").remove();
        $('#password').after('<span class="fail"> 비밀번호를 입력해주세요. </span>');
        
		return false;
	}
	else
	{	
		$("#password").nextAll("*").remove();
		return true;
	}	
};


//blur 이벤트 발생 시 처리
$('#userid').blur(idValidate);
$('#password').blur(pwValidate);



$('#login').click(function(e)
{

    if(idValidate() && pwValidate())
    {
    	$('#login-form').submit();
    }
    else if( !idValidate() )
    {
        //뒤에 span 태그는 하나만 있어야함 
        $('#userid').nextAll("*").remove();
        $('#userid').after('<span class="fail"> 아이디를 입력해주세요. </span>');
        
     }
    //남은 경우의 수는 password의 길이가 0인 경우 만 남게 됨 
    else
    { 
    	 //뒤에 span 태그는 하나만 있어야함 
        $('#password').nextAll("*").remove();
        $('#password').after('<span class="fail"> 비밀번호를 입력해주세요. </span>');
        
    }
             

              	
                	
 });
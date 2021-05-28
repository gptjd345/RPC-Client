
//  검색 옵션, 검색 키워드 , 현재 블록 정보는 쿠키에 저장하고 쿠키 만료 날짜는 1일로 설정한다. 
//  document.cookie = "cookie이름 = 쿠키값;  expires = UTC 날짜";
//  escape(cookieValue) : 쿠키는 기본적으로 데이터를 아스키 코드형식으로 받아들인다. 저장하려는 데이터가 
// 						아스키 문자가 아니라면(한글) 모두 유니코드 형식으로 변환해야한다. 
//  unescape(cookieValue); 쿠키값은 디코딩 후 값을 리턴
function setCookie(name,value)
{
  //  Date 형 변수 선언	
  var exdate = new Date();
  // Date객체의 getDate 메소드를 통해 현재 날짜를 받아오고 거기에 1을 더한다.(쿠키의 만료날짜는 1일)
  exdate.setDate(exdate.getDate() + 1);
  
  //  document.cookie 양식에 맞게 데이터 입력 
  var cookie_value= escape(value)+'; expires=' + exdate.toUTCString();
  document.cookie = name + '=' + cookie_value;

}

//  원하는 쿠키이름 입력 시 해당 쿠키 값을 리턴 
function getCookie(name) 
{
  var cookieName,cookieValue;
  // 쿠키 값에 입력된 데이터를 ;를 구분자로 나누어서 배열형태로 저장한다. 
  var totalValue = document.cookie.split(';');
 
 for(let i = 0 ; i < totalValue.length; i++)
 {
   // 쿠키 이름에 해당하는 부분
   cookieName = totalValue[i].substr(0, totalValue[i].indexOf('='));
   
   // 쿠키 값에 해당하는 부분
   cookieValue = totalValue[i].substr(totalValue[i].indexOf('=') + 1 );
   
   // 쿠키이름 양쪽 공백제거
   cookieName = $.trim(cookieName);
   if(cookieName == name)
   {
	 // 쿠키값은 디코딩 후 값을 리턴
     return unescape(cookieValue); 
   }
 }
 
} 


$('#delete').click(function(e) 
{
	let selectedRow = [];
	
	// checkbox의 value 속성에는 userid 값이 들어있다. 
	// checkbox의 부모 태그 중 tr 태그선택한다. 
	// tr태그의 display 속성을 none으로 지정하면 테이블에서 해당 로우를 안보이게 할 수 있다. 
	$('.table tr:visible input[name=checkbox]:checked').each(function(e){
		$(this).closest('tr').css('display','none');
		selectedRow.push($(this).val());
	});
	console.log("selectedRow : "+selectedRow)
	$.ajax({
	    url: "/member/delete.do",
	    type: "post",
	// traditional 옵션을 사용하면 배열전달가능 
	    traditional : true,
	    data: { "selectedRow" : selectedRow } ,
	    dataType: "text",
	    success: function(pageDTO)
	    {
        	alert("삭제 되었습니다.");    
	    }
	});
});



// 등록 창으로 이동
$('#regist').click(function(e)
{	
	let curBlock = $('input[name=curBlock]').val();
	let searchOption = $('input[name=searchOption]').val();
	let searchKey = $('input[name=searchKey]').val();
	
	window.location.replace("/member/regist?curBlock="+curBlock+ 
		"&searchOption="+searchOption+"&searchKey="+encodeURI(searchKey));
		
});



// 테이블과 페이저를 초기화한 html을 가져와서 특정 태그 아래에 뿌린다.  
var tableInit = function(e) 
{	
	var curBlock = getCookie("curBlock");
	var searchOption = getCookie("searchOption");
	var searchKey = getCookie("searchKey");
	
	console.log("curBlock : "+curBlock+"searchOption : "+searchOption+" searchKey :"+searchKey);
	var data = {"curBlock":curBlock, "searchOption":searchOption, "searchKey":searchKey};
	 $.ajax({
	        url: "./tableInit.do",
	        type: 'post',
	        data: JSON.stringify(data),
	        contentType : 'application/json; charset=utf=8',
	        success: function(result)
	        {
	        	//  id가 memberListPrint인 태그 아래에 동적으로 만들어 놓은 html 을 삽입한다..
	            $('#memberListPrint').html(result);
	            
	        }
	    });

};

// 테이블과 페이저 초기화 
tableInit();


// 이전 버튼 클릭시
var prevButtonClick = function(e)
{
	let curBlock = $('input[name=curBlock]').val();
	// 시작 블록에서 1을 뺀값이 이전 블록 값이다.
	let prevBlock = curBlock - (curBlock - 1) % 10 - 1 ;
	
	// 현재블록을 쿠키에 저장
	setCookie("curBlock",preBlock);
	
	// 테이블 초기화 함수 실행
	tableInit();	
	
};

// 다음 버튼 클릭시
var nextButtonClick = function(e)
{
	let curBlock = $('input[name=curBlock]').val();
	let startBlock = curBlock - (curBlock - 1) % 10 ;
	
	// 시작 블록에서 10을 더하면 다음 블록이다.
	let nextBlock = startBlock + 10;
	
	// 현재블록을 쿠키에 저장
	setCookie("curBlock",nextBlock);
	
	// 테이블 초기화 함수 실행
	tableInit();	
	
};


// 페이지 버튼 클릭시
var pageButtonClick = function()
{
	let pageBlock = $(event.target).val();
	console.log("pageBlock "+pageBlock);
	
	// 검색 옵션을 쿠키에 저장
	setCookie("curBlock",pageBlock);
	
	// 테이블 초기화 함수 실행
	tableInit();	
	
};

// 검색 버튼 클릭시 
var searchButtonClick = function(e)
{
	let searchOption = $('select[name=searchOption]').val();
	let searchKey = $('input[name=searchKey]').val();
	
	console.log("searchOption : "+searchOption+" searchKey :"+searchKey);
	
	// 검색 옵션을 쿠키에 저장
	setCookie("searchOption",searchOption);
	// 검색 키워드를 쿠키에 저장
	setCookie("searchKey",searchKey);
		
	// 테이블 초기화 함수 실행
	tableInit();
};



















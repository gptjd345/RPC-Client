



$('#delete').click(function(e) 
{
				
	$('input[name=checkbox]:checked').each(function(e){
		let value = $(this).val(); 
		$('#deleteForm').append($('<input/>',{type:'hidden',name:'list',value:value}));
	});
	
	$('#deleteForm').submit();
     
	
});


//테이블 초기화를 위한 데이터를 컨트롤러에서 가져온다. 
var tableInit = function(e) 
{	
	var curBlock = $('input[name=curBlock]').val();
	var searchOption = $('select[name=searchOption]').val();
	var searchKey = $('input[name=searchKey]').val();
	
	var data = {"curBlock":curBlock, "searchOption":searchOption, "searchKey":searchKey};
	 $.ajax({
	        url: "./tableInit.do",
	        type: 'post',
	        data: JSON.stringify(data),
	        contentType : 'application/json; charset=utf=8',
	        dataType : 'json',
	        success: function(map)
	        {
	        	//테이블 초기화
	            tableList(map);
	        }
	    });

};


var tableList = function(map)
{
		const list = map['list'];
		const totalPage = map['totalPage'];
		const pageDTO = map['pageDTO'];
		console.log(pageDTO);
		
		//테이블 목록을 지움
		$('.table>tbody').css('display','none');
		var tableList = "<tbody>";
		for(let i = 0; i < list.length; i++)
		{	
			tableList += "<tr>";
			tableList += "<td>"+"<input type='checkbox' name='checkbox' value='"+list[i]['userid']+"'/>"+"</td>";
			tableList += "<td>"+list[i]['membernum']+"</td>";
			tableList += "<td>"+"<a href='./modify?userid="+list[i]['userid']+"&curBlock="+pageDTO['curBlock']+"&searchOption="+pageDTO['searchOption']+"&searchKey="+pageDTO['searchKey']+"'>"+list[i]['userid']+"</a></td>";
			tableList += "<td>"+list[i]['name']+"</td>";
			tableList += "<td>"+formattingPhoneNum(list[i]['phonenum'])+"</td>";
			tableList += "<td>"+moment(list[i]['joindate']).format('YYYY-MM-DD HH:mm')+"</td>";
			tableList += "</tr>";	
			
		};
		tableList += "</tbody>";
		$('.table').append(tableList);
		
		let startBlock = pageDTO['curBlock'] - (pageDTO['curBlock'] - 1) % 10;
		//나머지가 잇는 경우 올림처리
		let lastBlock = Math.ceil(totalPage/10);
		
		//페이저 지움
		$('.table').next().css('display','none');
		
		//페이저
		var pager="<nav id='pager'>";
		let prevButton;
		let pageNumber;
		
		//이전버튼
		if(startBlock > 1)
		{	
			pager += "<button type='button' id='prevButton' onclick='prevButtonClick();'><i class='fas fa-angle-left' title='이전'></i></button>"
				
		}
		
		//실제페이지가 존재하는 마지막 블록보다 시작블록에서 9를 더한값이 더크면 실제페이지가 존재하는 마지막 블록이 화면에서 보여주는 마지막 블록이된다. 
		let endBlock = (startBlock+9 > lastBlock ) ? lastBlock : startBlock +9; 
		for(let i = startBlock ; i <= endBlock ; i++)
		{
			
			if(i == pageDTO['curBlock'])
			{
				console.log("i="+ i);
				pager += "<span style='color: red;'>"+i+"</span>"
				continue;
			}
			pager += "<button type='button' class='pagerNum' value='"+i+"'onclick='pageButtonClick();'>"+i+"</button>";
				
		}
		
		//다음버튼 예상되는 마지막블록보다 1큰값이 데이터가 존재하는 마지막 블록보다 작은경우(다음불록에 데이터가 있는 경우)
		if(startBlock + 10 <= lastBlock)
		{
			pager += "<button type='button' id='nextButton' onclick='nextButtonClick();'><i class='fas fa-angle-right' title='다음'></i></button>"
				
		}
	
		pager += "</nav>"
		
		//pager 그리기 
		$('.table').after(pager);
		
};

//전화번호 -(하이픈) 처리해서 보여준다. 애초에 사용자가 - 을 써도 되고 안써도 되도록 해서 일관된 화면을 보여주기 위함
let formattingPhoneNum = function(pnum)
{
	//하이픈 먼저 전부 제거 후 진행
	let phonenum = pnum;
	phonenum = phonenum.replace(/\-/g,"");
	
	let firstnum = phonenum.substring(0,3);
	
	//substring(startIndex,endIndex) 마지막 인덱스는 포함하지 않는다.
	let middlenum = phonenum.substring(3,(phonenum.length-4));

	let lastnum = phonenum.substring(phonenum.length-4);
	
	return firstnum+'-'+middlenum+'-'+lastnum;
}

//list 테이블 초기화
tableInit();


////버튼 클릭시 ajax 통신 
$('#searchButton').click(function(e)
{	
	let searchOption = $('input[name=searchOption]').val();
	let searchKey = $('input[name=searchKey]').val();
	
	console.log("searchOption : "+searchOption+" searchKey :"+searchKey);
	//검색옵션을 저장
	$('input[name=searchOption]').attr('value',searchOption);
	
	//검색 키워드 저장
	$('input[name=searchKey]').attr('value',searchKey);
	
	//테이블 초기화 함수 실행
	tableInit();		
});

//이전 버튼 클릭시
var prevButtonClick = function(e)
{
	let curBlock = $('input[name=curBlock]').val();
	//시작 블록에서 1을 뺀값이 이전 블록 값이다.
	let prevBlock = curBlock - (curBlock - 1) % 10 - 1 ;
	
	//현재 블록번호를 변경
	$('input[name=curBlock]').attr('value',prevBlock);
	
	//테이블 초기화 함수 실행
	tableInit();	
	
};

//다음 버튼 클릭시
var nextButtonClick = function(e)
{
	let curBlock = $('input[name=curBlock]').val();
	let startBlock = curBlock - (curBlock - 1) % 10 ;
	
	//시작 블록에서 10을 더하면 다음 블록이다.
	let nextBlock = startBlock + 10;
	
	//현재 블록번호를 변경
	$('input[name=curBlock]').attr('value',nextBlock);
	
	//테이블 초기화 함수 실행
	tableInit();	
	
};


//페이지 버튼 클릭시
var pageButtonClick = function()
{
	let pageBlock = $(event.target).val();
	console.log("pageBlock "+pageBlock);
	//현재 블록번호를 변경
	$('input[name=curBlock]').attr('value',pageBlock);
	
	//테이블 초기화 함수 실행
	tableInit();	
	
};

//등록 창으로 이동
$('#regist').click(function(e)
{	
	let curBlock = $('input[name=curBlock]').val();
	let searchOption = $('input[name=searchOption]').val();
	let searchKey = $('input[name=searchKey]').val();
	
	window.location.replace("/member/regist?curBlock="+curBlock+ 
		"&searchOption="+searchOption+"&searchKey="+encodeURI(searchKey));
		
});



















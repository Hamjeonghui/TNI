
	// https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=haennyy&logNo=221548061129
	$(document).ready(function(){ //페이지가 로딩되면
		$('.filter').each(function(index){// each함수로 filter라는 객체를 반복문처럼 가져옴. index라는 input으로 filter의 index가져오기
			$(this).attr('filter-index', index); // 각각의 index의 값을 filter-index에 저장
		}).click(function(){ // 클릭하면,
			var index = $(this).attr('filter-index'); //클릭된 객체의 index가 누군지 저장
			$('.filter[filter-index=' + index + ']').addClass('clicked_filter'); // 클릭된 객체에 clicked_filter라는 클래스를 부여(CSS)
			$('.filter[filter-index!=' + index + ']').removeClass('clicked_filter'); // 나머지 객체에는 CSS클래스 제거

		});
	});
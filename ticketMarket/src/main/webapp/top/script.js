document.addEventListener('DOMContentLoaded',()=>{
	let myPageBtn = document.getElementById('myPageButton');
	myPageBtn.addEventListener('click',()=>{
		window.location.href="/ticketMarket/loginForm.do";
	});
});



function call_js() {
	//UI객체참조변수 선언
	let slideshow = document.querySelector(".slideshow");
	let slideshow_slides = document.querySelector(".slideshow_slides");

	//<a href ="#"><img></a>  UI 객체배열 
	let slides = document.querySelectorAll(".slideshow_slides a");
	let prev = document.querySelector(".prev");
	let next = document.querySelector(".next");
	let indicators = document.querySelectorAll(".indicator a");
	//회전목마의 현재위치
	let currentIndex = 0;
	let timer = null;;
	let slideCount = slides.length;

	//회전목마 이미지를 우측으로 배치
	for (let i = 0; i < slideCount; i++) {
		let newLeft = i * 100 + `%`;
		slides[i].style.left = newLeft;
	}

	//회전목마를 움직인다() slideshow_slides 왼쪽으로 -100% 이동
	function gotoSlide(index) {
		currentIndex = index;
		let newLeft = index * -100 + '%';
		slideshow_slides.style.left = newLeft;
		indicators.forEach(event => {
			event.classList.remove("active");
		});
		indicators[currentIndex].classList.add("active");
	}
	//index =(0번부터 3번까지 3초간 gotoSlide(index))
	gotoSlide(0);

	//3초간 움직이며 이동
	function startTimer() {

		timer = setInterval(() => {
			currentIndex += 1;
			let index = currentIndex % slideCount;
			gotoSlide(index);
		}, 3000);
	}
	startTimer();

	//이벤트처리
	slideshow_slides.addEventListener("mouseenter", () => {
		clearInterval(timer);
	});
	slideshow_slides.addEventListener("mouseleave", () => {
		startTimer();
	});

	prev.addEventListener("mouseenter", () => {
		clearInterval(timer);
	});
	next.addEventListener("mouseenter", () => {
		clearInterval(timer);
	});



	prev.addEventListener("click", (event) => {
		//a tag 기본기능을 막는다.
		event.preventDefault();
		currentIndex -= 1;
		if (currentIndex < 0) {
			currentIndex = slideCount - 1;
		}
		gotoSlide(currentIndex);
	});

	next.addEventListener("click", (event) => {
		//a tag 기본기능을 막는다.
		event.preventDefault();
		currentIndex += 1;
		if (currentIndex > slideCount - 1) {
			currentIndex = 0;
		}
		gotoSlide(currentIndex);
	});

	indicators.forEach(event => {
		event.addEventListener("mouseenter", () => {
			clearInterval(timer);
		});
	});

	for (let i = 0; i < indicators.length; i++) {
		indicators[i].addEventListener("click", event => {
			event.preventDefault();

			gotoSlide(i);
		});
	};

}
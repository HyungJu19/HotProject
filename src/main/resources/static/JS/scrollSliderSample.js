// version1
// var currentPage = 1;
//
// document.addEventListener("wheel", function (event) {
//     if (event.deltaY > 0) {
//         nextPage();
//     } else {
//         prevPage();
//     }
// });
//
// function nextPage() {
//     if (currentPage < 3) {
//         currentPage++;
//         updatePages();
//     }
// }
//
// function prevPage() {
//     if (currentPage > 1) {
//         currentPage--;
//         updatePages();
//     }
// }
//
// function updatePages() {
//     const pages = document.querySelectorAll(".page");
//     pages.forEach(page => {
//         const pageIndex = parseInt(page.id.replace("page", ""));
//         const translation = 100 * (pageIndex - currentPage);
//         page.style.transform = `translateX(${translation}%)`;
//     });
// }



// deltaY - 마우스휠 Y축(=상하=세로) 스크롤량 반환.
// 읽기전용 속성(값 설정 불가)
// 주요 최신 브라우저 모두 지원(사파리 빼고)


// event.deltaY

// 스크롤량 나타내는 Double 자료형의 숫자 반한.

// 위로 스크롤: 음(-)의 숫자 반환.
// 아래로 스크롤: 양(+)의 숫자 반환.
// 스크롤 안 하면: 0 반환.

//
// window.scrollTo()의 두가지 사용법.
//
//     참고) MDN - Window.scrollTo()
// https://developer.mozilla.org/en-US/docs/Web/API/Window/scrollTo
//
//
//
//
//
//     1) window.scrollTo(xpos, ypos)
//
//
// 파라미터로 주어진 xpos는 x좌표, 즉 가로 위치를 말하는 것이고, ypos는 y좌표, 즉 세로 위치를 말하는 것이다.
//
//
// 2) window.scrollTo({top:0, left:0, behavior:'auto'});
//
//
// 더 자세한 내용은 다음 w3 문서 링크에서 확인할 수 있다.
//
//     CSSOM View Module - Extensions to the Window Interface
// https://www.w3.org/TR/cssom-view-1/#extensions-to-the-window-interface
//
//     (CSSOM (CSS Object Model)이란 것도 있다. 자바스크립트에서 CSS를 동적으로 조작할 수 있게 해주는 API를 말한다.)
//
//
//
// window.scrollTo 안에 필요한 option들을 입력하면 된다.
//
//     모든 값이 필수는 아니다. 순서 또한 상관없다.
//
//     위 옵션들로 css의 top, left, scroll-behavior 속성을 사용하여 화면을 이동시킨다.
//
//     top은 세로 위치, left는 가로 위치, scroll-behavior은 스크롤 효과를 지정하는 속성이다.
//
//
//
//     behavior의 값에는 auto, instant, smooth가 있다. (문자이므로 따옴표가 필요하다.)
//
// auto는 기본값이며, 바로 위치로 이동한다. instant도 같은 동작을 한다.
//
//     smooth는 부드럽게 이동하는 애니메이션 효과를 보여준다.
//
//
//
//
//
//
//
//
//
// 2. 좌표 알아오기
//
//
// 이제 사용 방법을 알았으니, 이동시키려는 위치를 숫자(단위는 자동으로 pixel)로 넣어 사용하면 된다.
//
//     하지만 숫자를 직접 입력한다면 남들이 봤을 때 어느 위치인지 한눈에 파악하기도 힘들고, HTML 태그가 수정되어 위치가 바뀌었을 때 다시 수정해야 한다는 불편함이 있다.
//
//     자바스크립트의 querySelector와 offsetTop이나 offsetLeft로 직접 해당 태그의 위치를 얻어올 수 있다.
//
//     document.querySelector로 먼저 위치를 알아오려는 요소를 가져온다.
//
//     offsetTop으로 해당 요소의 top 위치(마진 값까지 포함한다고 한다)를 가져온다.
//
//     offsetLeft는 이름으로 유추할 수 있듯이 left 위치를 가져온다. 역시 마진 값도 포함한다.
//
//
//
//
//
//     var location = document.querySelector("#move").offsetTop;
//
//
// 위 코드처럼 id가 move인 요소의 top 위치를 가져올 수 있다.
//
//
//
//     그럼
//
// window.scrollTo({top:location, behavior:'smooth'});
// 이렇게 한다면, 해당 위치로 부드럽게 이동할 것이다.
//
//     탭 메뉴를 클릭할 때 위 코드가 실행되게 한다면, 탭 메뉴를 클릭했을 때 특정 위치로 이동하는 코드를 구현할 수 있을 것이다.
//
//
//
//
//
// +) 만약 위에 고정 메뉴가 있다면, 스크롤이 이동하면서 그 메뉴에 내용 윗부분이 가려질 수 있다.
//
//     var menuHeight = document.querySelector(".menu").offsetHeight;
//
// window.scrollTo({top:location - menuHeight, behavior:'smooth'});
// 이렇게 offsetHeight로 해당 요소의 높이를 구하여 top 좌표에서 빼면 된다.
//
//
//
//
//
//     종합적인 코드는 다음과 같다.
//
//     var menuHeight = document.querySelector(".menu").offsetHeight;
//
// var location = document.querySelector("#move").offsetTop;
//
// window.scrollTo({top:location - menuHeight, behavior:'smooth'});
//
//
//
//
//
//
//
//
// 다음 사이트에서 더 다양한 scroll 메서드를 확인할 수 있다.
//
//     iamdustan.com의 smooth scroll
// https://iamdustan.com/smoothscroll/
//




// version2
document.addEventListener('DOMContentLoaded', function () {
    const slideContainer = document.querySelector('.slide-container');
    // 슬라이드 할 페이지 전체를 감싼 컨테이너 정의
    const slides = document.querySelectorAll('.slide');
    let currentIndex = 0;

    window.addEventListener('resize', function () {
        updateSlideHeight();
    });

    function updateSlideHeight() {
        const slideHeight = window.innerHeight;
        slides.forEach((slide) => {
            slide.style.height = `${slideHeight}px`;
        });

        scrollToSlide(currentIndex);
    }

    function scrollToSlide(index) {
        const newPosition = index * window.innerHeight;
        console.log("scorllTo")
        window.scrollTo({
            top: newPosition,
            behavior: 'smooth'
        });
    }

    slideContainer.addEventListener('wheel', function (event) {
        // slideContainer 내에서 스크롤 할시 이벤트 추가
        console.log("wheel event")
        event.preventDefault();

        if (event.deltaY > 0) {
            // 아래로 스크롤
            currentIndex = Math.min(currentIndex + 1, slides.length - 1);
        } else {
            // 위로 스크롤
            currentIndex = Math.max(currentIndex - 1, 0);
        }

        scrollToSlide(currentIndex);
    });

    // 초기 설정
    updateSlideHeight();
});
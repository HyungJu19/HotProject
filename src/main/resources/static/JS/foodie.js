// 슬라이드
// document.addEventListener('DOMContentLoaded', function () {
//     const slideContainer = document.querySelector('.slide-container');  // 슬라이드 할 페이지 전체를 감싼 컨테이너 정의
//     const slides = document.querySelectorAll('.slide');     //  슬라이드 될 페이지 정의
//     let currentIndex = 0; // 현재 보여지는 슬라이드의 인덱스를 나타내는 변수를 초기화
//
//     function scrollToSlide(index) {  //  슬라이드를 특정 인덱스로 스크롤하는 함수를 정의
//         const clientWidth = slides[index].clientWidth; // 슬라이드의 길이를 가져옴
//         const newPosition = index * clientWidth;
//         slideContainer.scrollTo({
//             left: newPosition,
//             behavior: 'smooth'
//         });
//         console.log(newPosition);
//
//
//     }
//
//     slideContainer.addEventListener('wheel', function (event) {
//         // slideContainer 내에서 스크롤 할시 이벤트 추가
//         event.preventDefault();  //  기본 스크롤 동작을 막습니다.
//
//         if (event.deltaY > 0) {
//             // 아래로 스크롤
//             currentIndex = Math.min(currentIndex + 1, slides.length - 1);
//         } else {
//             // 위로 스크롤
//             currentIndex = Math.max(currentIndex - 1, 0);
//
//         }
//         console.log(event.deltaY);
//         console.log(currentIndex);
//         scrollToSlide(currentIndex);
//         console.log(currentIndex);
//
//     });
//
//
// });


$(document).ready(function () {
        let currentLat;
        let currentLng;
        let map;
        let marker;
        let markers = [];

        // 현재 위치 좌표를 비동기적으로 가져오는 함수
        let cachedLocation = null;

        function getLocation() {
            return new Promise((resolve, reject) => {
                if (cachedLocation) {
                    // 캐시된 위치 사용
                    currentLat = cachedLocation.coords.latitude;
                    currentLng = cachedLocation.coords.longitude;
                    resolve();
                } else if (navigator.geolocation) {
                    const options = {
                        timeout: 10000,
                    };

                    navigator.geolocation.getCurrentPosition(
                        position => {
                            currentLat = position.coords.latitude;
                            currentLng = position.coords.longitude;
                            resolve();
                        },
                        error => {
                            console.error("Error getting location:", error);
                            reject(error);
                        },
                        options
                    );
                } else {
                    reject(new Error("Geolocation is not supported."));
                }
            });
        }


        async function initMap() {
            const {Map} = await google.maps.importLibrary("maps");
            // const {AdvancedMarkerElement} = await google.maps.importLibrary("marker");

            console.log("현재 위도:", currentLat);
            console.log("현재 경도:", currentLng);

            map = new Map(document.getElementById("map"), {
                center: {lat: currentLat, lng: currentLng},
                zoom: 14,
                mapId: "2c7993f711e6d200",
            });

            //  초기마커 생성
            // marker = new AdvancedMarkerElement({
            marker = new google.maps.Marker({
                map,
                position: {lat: currentLat, lng: currentLng},
                icon : {
                    url: '../../IMG/person.png',
                    scaledSize: new google.maps.Size(40, 40), // Adjust the size as needed
                },
            });

            google.maps.event.addListener(map, 'click', function (event) {
                placeMarker(event.latLng); // 클릭된 위치에 마커 표시
            });
        }

        function placeMarker(location) {
            // 이전 마커 삭제
            if (marker) {
                marker.setMap(null);
            }
            // 기존의 AdvancedMarkerElement를 사용하여 마커를 생성하고 추가
            // marker = new AdvancedMarkerElement({
            marker = new google.maps.Marker({
                map,
                position: location,
                icon : {
                    url: '../../IMG/person.png',
                    scaledSize: new google.maps.Size(40, 40), // Adjust the size as needed
                },
            });

            // 클릭된 위치의 좌표 얻기
            const latitude = location.lat();
            const longitude = location.lng();
            console.log("Latitude: " + latitude + ", Longitude: " + longitude);
            map.setCenter(location);
            clickFoodie(longitude, latitude);


        }

        // 여기에서 마커 생성 및 추가 로직을 구현할 수 있습니다.


        // 페이지 로드 시 현재 위치 가져오기 시도

        getLocation()
            .then(() => {
                // 현재 위치 좌표를 가져온 후에 Google Map 초기화 함수 호출
                initMap();
            })
            .catch(error => {
                console.error("Failed to get location:", error);
            });


//     지역, 종류 버튼 클릭 이벤트


// 투어4.0 api 호출
//   -> x,y 좌표 가져오기

        async function clickFoodie(mapx, mapy) {
            try {

                const data = await fetchData(mapx, mapy);
                const foodie = data.localFoodieResponse.response.body.items.item;

                const foodieContainer = document.getElementById("foodieList");
                foodieContainer.innerHTML = ""; // 기존 리스트 초기화

                // 구글 맵 마커 클리어
                clearMarkers();

                if (foodie && foodie.length > 0) {
                    const foodieContainer = document.getElementById("foodieList");

                    foodie.forEach((item) => {
                        if (item.mapx && item.mapy) {
                            // 좌표가 있는 경우에만 처리
                            const latitude = parseFloat(item.mapy);
                            const longitude = parseFloat(item.mapx);
                            if (item.firstimage && item.title) {

                                // Google Maps에서 LatLng 객체 생성
                                const location = new google.maps.LatLng(latitude, longitude);

                                // Google Maps 마커 생성 및 추가
                                addMarker(location, item.title);
                            }
                            // Create card container
                            const cardContainer = document.createElement("div");
                            cardContainer.classList.add("card", "mb-3");
                            // Create image element
                            const listImg = document.createElement("img");
                            listImg.src = item.firstimage;
                            listImg.classList.add("card-img-top");

                            // Create card body container
                            const cardBody = document.createElement("div");
                            cardBody.classList.add("card-body");

                            // Create title element
                            const title = document.createElement("h5");
                            title.textContent = item.title;
                            title.classList.add("card-title");

                            const cardWrapper = document.createElement("div");
                            cardWrapper.classList.add("col-md-3");

                            // Append image and title to the card body
                            cardContainer.appendChild(listImg);
                            cardBody.appendChild(title);

                            // Append card body to the card container
                            cardContainer.appendChild(cardBody);


                            cardWrapper.appendChild(cardContainer);
                            // Append the card container to the parent container
                            foodieContainer.appendChild(cardWrapper);
                        }
                    });
                }

            } catch
                (error) {
                console.error("데이터를 가져오는 동안 오류 발생:", error);
                console.error("에러 위치:", error.stack);
            }
        }

        //
        initMap();
        // addMarker(mapx, mapy);


function addMarker(location, title) {
    const marker = new google.maps.Marker({
        map,
        position: location,
        title: title
    });
    markers.push(marker); // 마커 배열에 추가
}

// 구글 맵의 모든 마커를 클리어하는 함수
function clearMarkers() {
    for (const marker of markers) {
        marker.setMap(null);
    }
    markers = []; // 마커 배열 초기화
}

async function fetchData(mapx, mapy) {
    const TIMEOUT_DURATION = 900; // 타임아웃 기간 (0.3초)
    const response = await fetch(`/searchDetail12/${mapx}/${mapy}`, {});
    const data = await response.json();
    return data;
}
    })




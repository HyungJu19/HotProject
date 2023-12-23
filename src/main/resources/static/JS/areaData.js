
let map;
let geocoder;

document.addEventListener('DOMContentLoaded', async function () {

initMap();

});
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 7,
        center: { lat: 36.5, lng: 127.9 } // 초기 지도 중심 좌표
    });
    geocoder = new google.maps.Geocoder();
}

function showOnMap() {``
    let address = document.getElementById('addr1').value;
    geocoder.geocode({ 'address': address }, function(results, status) {
        if (status === 'OK') {
            document.getElementById('mapx').value = results[0].geometry.location.lat();
            document.getElementById('mapy').value = results[0].geometry.location.lng();
            map.setCenter(results[0].geometry.location);
            map.setZoom(15);
            let marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });

            // 상세주소 받아오기
            if (results[0].formatted_address) {
                document.getElementById('fullAddress').value = results[0].formatted_address;
            } else {
                console.log('상세주소를 찾을 수 없습니다.');
            }

            // 시/도와 구/군 정보 추출
            let zipcode = '';
            let areacode = '';
            let sigungucode = '';
            let sido = '';


// 주소 컴포넌트를 순회하며 우편번호, 시/도 코드, 시군구 코드를 추출하는 부분
            for (let i = 0; i < results[0].address_components.length; i++) {
                const component = results[0].address_components[i];
                const types = component.types;

                if (types.includes('postal_code')) {
                    zipcode = component.long_name;

                }

                if (types.includes('administrative_area_level_1')) {
                    areacode = component.long_name;
                    sido = component.long_name;
                    const selectedArea = areaData.find((area) => area.name === areacode);
                    if (selectedArea) {
                        areacode = selectedArea.id;

                    }
                }

                if (types.includes('administrative_area_level_2') || types.includes('locality') || types.includes('sublocality')) {
                    sigungucode = component.long_name;
                }
                if (sido === '서울특별시'){
                    const sigungu = seoulSigungu.find((area) => area.name === sigungucode);
                    if (sigungu){
                        sigungucode = sigungu.id;

                    }
                }else if (sido ==='인천광역시'){
                    const sigungu = incheonSigungu.find(area =>area.name === sigungucode);
                    if (sigungu){
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='대전광역시'){
                    const sigungu = daejeonSigungu.find(area =>area.name === sigungucode);
                    if (sigungu){
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='대구광역시'){
                    const sigungu = daeguSigungu.find(area =>area.name === sigungucode);
                    if (sigungu){
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='광주광역시'){
                    const sigungu = gwangjuSigungu.find(area =>area.name === sigungucode);
                    if (sigungu){
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='부산광역시') {
                    const sigungu = busanSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='울산광역시') {
                    const sigungu = ulsanSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='세종특별자치시') {

                        sigungucode = 1;

                }else if (sido ==='경기도') {
                    const sigungu = gyeonggiSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='강원도') {
                    const sigungu = gangwonSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='충청북도') {
                    const sigungu = chungbukSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='충청남도') {
                    const sigungu = chungnamSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='경상북도') {
                    const sigungu = gyeongbukSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='경상남도') {
                    const sigungu = gyeongnamSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='전라북도') {
                    const sigungu = jeonbukSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='전라남도') {
                    const sigungu = jeonnamSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }else if (sido ==='제주특별자치도') {
                    const sigungu = jejuSigungu.find(area => area.name === sigungucode);
                    if (sigungu) {
                        sigungucode = sigungu.id;
                    }
                }

            }




            if (zipcode !== '') {
                document.getElementById('zipcode').value = zipcode;
            } else {
                console.log('우편번호 정보를 찾을 수 없습니다.');
            }
            if (sigungucode !== '') {
                document.getElementById('areacode').value = areacode;
            } else {
                console.log('시/도 정보를 찾을 수 없습니다.');
            }
            if (sigungucode !== '') {
                document.getElementById('sigungucode').value = sigungucode;
            } else {
                console.log('구/군 정보를 찾을 수 없습니다.');
            }
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}
const areaData = [
    { id: 1, name: '서울특별시' },
    { id: 2, name: '인천광역시' },
    { id: 3, name: '대전광역시' },
    { id: 4, name: '대구광역시' },
    { id: 5, name: '광주광역시' },
    { id: 6, name: '부산광역시' },
    { id: 7, name: '울산광역시' },
    { id: 8, name: '세종특별자치시' },
    { id: 31, name: '경기도' },
    { id: 32, name: '강원도' },
    { id: 33, name: '충청북도' },
    { id: 34, name: '충청남도' },
    { id: 35, name: '경상북도' },
    { id: 36, name: '경상남도' },
    { id: 37, name: '전라북도' },
    { id: 38, name: '전라남도' },
    { id: 39, name: '제주특별자치도' }
];
const seoulSigungu = [
    { id: 1, name: '강남구' },
    { id: 2, name: '강동구' },
    { id: 3, name: '강북구' },
    { id: 4, name: '강서구' },
    { id: 5, name: '관악구' },
    { id: 6, name: '광진구' },
    { id: 7, name: '구로구' },
    { id: 8, name: '금천구' },
    { id: 9, name: '노원구' },
    { id: 10, name: '도봉구' },
    { id: 11, name: '동대문구' },
    { id: 12, name: '동작구' },
    { id: 13, name: '마포구' },
    { id: 14, name: '서대문구' },
    { id: 15, name: '서초구' },
    { id: 16, name: '성동구' },
    { id: 17, name: '성북구' },
    { id: 18, name: '송파구' },
    { id: 19, name: '양천구' },
    { id: 20, name: '영등포구' },
    { id: 21, name: '용산구' },
    { id: 22, name: '은평구' },
    { id: 23, name: '종로구' },
    { id: 24, name: '중구' },
    { id: 25, name: '중랑구' }
    // 다른 구들도 이어서 추가하세요.
];
const incheonSigungu = [
    { id: 1, name: '강화군' },
    { id: 2, name: '계양구' },
    { id: 3, name: '미추홀구' },
    { id: 4, name: '남동구' },
    { id: 5, name: '동구' },
    { id: 6, name: '부평구' },
    { id: 7, name: '서구' },
    { id: 8, name: '연수구' },
    { id: 9, name: '옹진군' },
    { id: 10, name: '중구' }
    // 다른 구들도 이어서 추가하세요.
];
const daejeonSigungu = [
    { id: 1, name: '대덕구' },
    { id: 2, name: '동구' },
    { id: 3, name: '서구' },
    { id: 4, name: '유성구' },
    { id: 5, name: '중구' }
    // 다른 구들도 이어서 추가하세요.
];
const daeguSigungu = [
    { id: 1, name: '남구' },
    { id: 2, name: '달서구' },
    { id: 3, name: '달성군' },
    { id: 4, name: '동구' },
    { id: 5, name: '북구' },
    { id: 6, name: '서구' },
    { id: 7, name: '수성구' },
    { id: 8, name: '중구' },
    { id: 9, name: '군위군' }
    // 다른 구들도 이어서 추가하세요.
];

const gwangjuSigungu = [
    {id: 1, name: '광신구'},
    {id: 2, name: '남구'},
    {id: 1, name: '동구'},
    {id: 1, name: '북구'},
    {id: 1, name: '서구구'}
];

const busanSigungu = [
    {id: 1, name: '강서구'},
    {id: 2, name: '금정구'},
    {id: 3, name: '기장군'},
    {id: 4, name: '남구'},
    {id: 5, name: '동구'},
    {id: 6, name: '동래구'},
    {id: 7, name: '부산진구'},
    {id: 8, name: '북구'},
    {id: 9, name: '사상구'},
    {id: 10, name: '사하구'},
    {id: 11, name: '서구'},
    {id: 12, name: '수영구'},
    {id: 13, name: '연제구'},
    {id: 14, name: '영도구'},
    {id: 15, name: '중구'},
    {id: 16, name: '해운대구'},
]
const ulsanSigungu = [
    { id: 1, name: '중구' },
    { id: 2, name: '남구' },
    { id: 3, name: '동구' },
    { id: 4, name: '북구' },
    { id: 5, name: '울주군' }
    // 다른 구들도 이어서 추가하세요.
];
const sejongSigungu = [
    { id: 1, name: '세종특별자치시' }
];

const gyeonggiSigungu = [
    { id: 1, name: '가평군' },
    { id: 2, name: '고양시' },
    { id: 3, name: '과천시' },
    { id: 4, name: '광명시' },
    { id: 5, name: '광주시' },
    { id: 6, name: '구리시' },
    { id: 7, name: '군포시' },
    { id: 8, name: '김포시' },
    { id: 9, name: '남양주시' },
    { id: 10, name: '동두천시' },
    { id: 11, name: '부천시' },
    { id: 12, name: '성남시' },
    { id: 13, name: '수원시' },
    { id: 14, name: '시흥시' },
    { id: 15, name: '안산시' },
    { id: 16, name: '안성시' },
    { id: 17, name: '안양시' },
    { id: 18, name: '양주시' },
    { id: 19, name: '양평군' },
    { id: 20, name: '여주시' },
    { id: 21, name: '연천군' },
    { id: 22, name: '오산시' },
    { id: 23, name: '용인시' },
    { id: 24, name: '의왕시' },
    { id: 25, name: '의정부시' },
    { id: 26, name: '이천시' },
    { id: 27, name: '파주시' },
    { id: 28, name: '평택시' },
    { id: 29, name: '포천시' },
    { id: 30, name: '하남시' },
    { id: 31, name: '화성시' }
];
const gangwonSigungu = [
    { id: 1, name: '강릉시' },
    { id: 2, name: '고성군' },
    { id: 3, name: '동해시' },
    { id: 4, name: '삼척시' },
    { id: 5, name: '속초시' },
    { id: 6, name: '양구군' },
    { id: 7, name: '양양군' },
    { id: 8, name: '영월군' },
    { id: 9, name: '원주시' },
    { id: 10, name: '인제군' },
    { id: 11, name: '정선군' },
    { id: 12, name: '철원군' },
    { id: 13, name: '춘천시' },
    { id: 14, name: '태백시' },
    { id: 15, name: '평창군' },
    { id: 16, name: '홍천군' },
    { id: 17, name: '화천군' },
    { id: 18, name: '횡성군' }
];
const chungbukSigungu = [
    {id: 1, name: '괴산군'},
    {id: 2, name: '단양군'},
    {id: 3, name: '보은군'},
    {id: 4, name: '영동군'},
    {id: 5, name: '옥천군'},
    {id: 6, name: '음성군'},
    {id: 7, name: '제천시'},
    {id: 8, name: '지천군'},
    {id: 9, name: '청원군'},
    {id: 10, name: '청주시'},
    {id: 11, name: '충주시'},
    {id: 12, name: '증평군'}
]
const chungnamSigungu = [
    { id: 1, name: '공주시' },
    { id: 2, name: '금산군' },
    { id: 3, name: '논산시' },
    { id: 4, name: '당진시' },
    { id: 5, name: '보령시' },
    { id: 6, name: '부여군' },
    { id: 7, name: '서산시' },
    { id: 8, name: '서천군' },
    { id: 9, name: '아산시' },
    { id: 10, name: '예산군' },
    { id: 11, name: '천안시' },
    { id: 12, name: '청양군' },
    { id: 13, name: '태안군' },
    { id: 14, name: '홍성군' }
];
const gyeongbukSigungu = [
    { id: 1, name: '경산시' },
    { id: 2, name: '경주시' },
    { id: 3, name: '고령군' },
    { id: 4, name: '구미시' },
    { id: 5, name: '김천시' },
    { id: 6, name: '문경시' },
    { id: 7, name: '봉화군' },
    { id: 8, name: '상주시' },
    { id: 9, name: '성주군' },
    { id: 10, name: '안동시' },
    { id: 11, name: '영덕군' },
    { id: 12, name: '영양군' },
    { id: 13, name: '영주시' },
    { id: 14, name: '영천시' },
    { id: 15, name: '예천군' },
    { id: 16, name: '울릉군' },
    { id: 17, name: '울진군' },
    { id: 18, name: '의성군' },
    { id: 19, name: '청도군' },
    { id: 20, name: '청송군' },
    { id: 21, name: '칠곡군' },
    { id: 22, name: '포항시' }
];
const gyeongnamSigungu = [
    { id: 1, name: '거제시' },
    { id: 2, name: '거창군' },
    { id: 3, name: '고성군' },
    { id: 4, name: '김해시' },
    { id: 5, name: '남해군' },
    { id: 6, name: '마산시' },
    { id: 7, name: '밀양시' },
    { id: 8, name: '사천시' },
    { id: 9, name: '산청군' },
    { id: 10, name: '양산시' },
    { id: 12, name: '의령군' },
    { id: 13, name: '진주시' },
    { id: 14, name: '진해시' },
    { id: 15, name: '창녕군' },
    { id: 16, name: '창원시' },
    { id: 17, name: '통영시' },
    { id: 18, name: '하동군' },
    { id: 19, name: '함안군' },
    { id: 20, name: '함양군' },
    { id: 21, name: '합천군' }
];
const jeonbukSigungu = [
    { id: 1, name: '고창군' },
    { id: 2, name: '군산시' },
    { id: 3, name: '김제시' },
    { id: 4, name: '남원시' },
    { id: 5, name: '무주군' },
    { id: 6, name: '부안군' },
    { id: 7, name: '순창군' },
    { id: 8, name: '완주군' },
    { id: 9, name: '익산시' },
    { id: 10, name: '임실군' },
    { id: 11, name: '장수군' },
    { id: 12, name: '전주시' },
    { id: 13, name: '정읍시' },
    { id: 14, name: '진안군' }
];
const jeonnamSigungu = [
    { id: 1, name: '강진군' },
    { id: 2, name: '고흥군' },
    { id: 3, name: '곡성군' },
    { id: 4, name: '광양시' },
    { id: 5, name: '구례군' },
    { id: 6, name: '나주시' },
    { id: 7, name: '담양군' },
    { id: 8, name: '목포시' },
    { id: 9, name: '무안군' },
    { id: 10, name: '보성군' },
    // 추가적인 시군구를 계속해서 나열해 나갈 수 있습니다.


];
// 제주도 시군구
const jejuSigungu = [
    { id: 1, name: '남제주군' },
    { id: 2, name: '북제주군' },
    { id: 3, name: '서귀포시' },
    { id: 4, name: '제주시' },
    // 필요한 만큼 추가 가능
];
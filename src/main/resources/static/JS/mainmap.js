
var currentlyHighlightedArea;

// 도넛 차트 인스턴스를 전역 변수로 저장
var myDonutChart;

// 도시 이름 배열을 전역 변수로 저장
var cities = [];


function moveImage(area) {
    const textContent = document.querySelector(`#${area}TextDiv`);
    textContent.style.backgroundColor = '#000'; // 배경색을 검정색으로 변경
    textContent.style.color = '#fff'; // 텍스트 색상을 흰색으로 변경
    const textContent1 = document.querySelector(` #${area}TextDiv .areaText`);
    textContent1.style.color = '#fff'; // 텍스트 색상을 흰색으로 변경
    const image = document.getElementById(`${area}Image`);
    let scaleValue = 'scale(1.0)'; // 기본 스케일 값

    // 도시에 따라 다른 스케일 적용
    if (area === 'seoul' || area === 'incheon' || area === 'sejong' || area === 'gwangju' || area === 'daejeon'|| area === 'ulsan'|| area === 'busan'|| area === 'daegu'|| area === 'jeju') {
        scaleValue = 'scale(1.2)';
    } else if (area === 'gyeonggi' || area === 'gangwon'|| area === 'gyeongsangbuk'|| area === 'jeollabuk'|| area === 'chungcheongnam'|| area === 'chungcheong'|| area === 'gyeongsangnam'|| area === 'jeollanam') {
        scaleValue = 'scale(1.1)';
    }

    image.style.transform = scaleValue;
    currentlyHighlightedArea = area;

    // 도넛 차트 세그먼트 강조
    highlightDoughnutSegment(area, true);


}
function resetImage(area) {
    const textContent = document.querySelector(`#${area}TextDiv `);
    textContent.style.backgroundColor = '#fff'; // 배경색을 원래대로 흰색으로 변경
    textContent.style.color = '#000'; // 텍스트 색상을 원래대로 검정색으로 변경
    const textContent1 = document.querySelector(`#${area}TextDiv .areaText`);
    textContent1.style.color = '#000'; // 텍스트 색상을 원래대로 검정색으로 변경
    const image = document.getElementById(`${area}Image`);
    image.style.transform = 'scale(1.0)'; // 이미지를 원래 크기로 되돌림

    highlightDoughnutSegment(area, false);
    }

function highlightDoughnutSegment(area, highlight) {
    var segmentIndex = cities.indexOf(area);
    if (segmentIndex !== -1 && myDonutChart) {
        var segment = myDonutChart.getDatasetMeta(0).data[segmentIndex];
        if (highlight) {
            // 강조 스타일 변경
            segment.custom = segment.custom || {};
            segment.custom.backgroundColor = '#FF0000';
        } else {
            // 스타일 초기화
            delete segment.custom;
        }
        myDonutChart.update();
    }
}
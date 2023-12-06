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



}
function resetImage(area) {
    const textContent = document.querySelector(`#${area}TextDiv `);
    textContent.style.backgroundColor = '#fff'; // 배경색을 원래대로 흰색으로 변경
    textContent.style.color = '#000'; // 텍스트 색상을 원래대로 검정색으로 변경
    const textContent1 = document.querySelector(`#${area}TextDiv .areaText`);
    textContent1.style.color = '#000'; // 텍스트 색상을 원래대로 검정색으로 변경
    const image = document.getElementById(`${area}Image`);
    image.style.transform = 'scale(1.0)'; // 이미지를 원래 크기로 되돌림
}
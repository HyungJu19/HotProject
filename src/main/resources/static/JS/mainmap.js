function moveImage(area) {
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
    const image = document.getElementById(`${area}Image`);
    image.style.transform = 'scale(1.0)';
}
document.addEventListener('DOMContentLoaded', function () {
    sendRequestToOtherPage(); // 페이지 로드 시 함수 실행
});
function sendRequestToOtherPage() {
    var token = localStorage.getItem('accessToken');

    if (token) { // 토큰이 존재할 때만 헤더에 추가하여 요청 보내기
        console.log("메인페이지 " + token)
        debugger;

        fetch('/main', {
            headers: {
                Authorization: token
            }

        })

            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        // 토큰이 없을 때의 처리
        console.log('토큰이 없습니다.');
    }
}

document.addEventListener("DOMContentLoaded", function() {
    var logoutLink = document.getElementById("logout-link");

    if (logoutLink) {
        logoutLink.addEventListener("click", function(event) {
            event.preventDefault(); // 기본 동작 방지

            // 로그아웃 API 호출
            fetch('/api/user/logout', {
                method: 'POST' // 또는 다른 메소드 (GET, DELETE 등) 사용 가능
            })
                .then(response => {
                    if (!response.ok) {
                        alert('로그아웃 요청이 실패했습니다.');
                    }
                    alert('로그아웃 성공');
                    window.location.href = '/main';
                })
                .catch(error => {
                    console.error('오류 발생:', error);
                    // 오류 발생 시 실행할 작업 추가 가능
                });
        });
    }
});
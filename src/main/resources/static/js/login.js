// 로그 기능
document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('.login-form');
    signupForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const providerId = document.getElementById('providerId').value;
        const password = document.getElementById('password').value;

        fetch('/api/login', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                providerId: providerId,
                password: password
            })
        })

            .then(response => {
                if (!response.ok) {
                    throw new Error('로그인 실패');
                }
                const accessToken = response.headers.get('Authorization'); // 헤더에서 토큰 추출
                if (!accessToken) {
                    throw new Error('토큰이 없습니다');
                }
                console.log("로그인 " + accessToken)
                return accessToken;
            })
            .then(accessToken => {
                // 토큰을 로컬 스토리지에 저장하거나 필요한 작업 수행
                localStorage.setItem('accessToken', accessToken);
                alert("로그인 성공");
                location.replace('/main');
            })
            .catch(error => {
                alert(error.message);
                location.replace('/login');
            });
    });
});
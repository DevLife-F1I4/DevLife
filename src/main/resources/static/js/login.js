// 회원가입 기능
const signupButton = document.getElementById('signup-btn');

if (signupButton) {
    signupButton.addEventListener('click', event => {
        const signupData = {
            providerId: document.getElementById('providerId').value,
            password: document.getElementById('password').value,
            nickname: document.getElementById('nickname').value
        };

        fetch('/api/signup', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(signupData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('회원가입에 실패했습니다.');
                }
                return response.json();
            })
            .then(data => {
                alert(data.message); // 서버로부터 받은 응답 메시지를 출력
                window.location.replace('/main'); // 회원가입 완료 후 메인 페이지로 이동
            })
            .catch(error => {
                console.error('Error:', error);
                alert('회원가입에 실패했습니다.');
            });
    });
}
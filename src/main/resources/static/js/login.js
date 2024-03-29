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
            if (response.ok) {
                alert("로그인 성공");
                location.replace('/main');
            } else {
                alert("로그인 실패");
                location.replace('/login');
            }
        })
            .catch(error => console.error('Error:', error));
    });
});
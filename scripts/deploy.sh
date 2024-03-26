# 도커 컨테이너 목록 조회 후 해당 컨테이너가 실행중이라면 중지하고 제거
sudo docker ps -a -q --filter "name=app" | grep -q . && docker stop app && docker rm app | true

# 기존 이미지 삭제
sudo docker rmi pgmjun/cicdstudy:latest

# 도커 허브에서 이미지 pull --> 태그가 latest
# 도커허브ID/도커허브REPOSITORY:TAG
sudo docker pull pgmjun/cicdstudy:latest

# 도커 컨테이너 실행 --> 호스트의 8080 포트와 컨테이너의 8080 포트를 연결 & 컨테이너에 환경변수 파일 읽어옴
# --env-file --> 환경변수를 저장할 파일명 => 운영용 EC2에 Secrets Manager 사용하기 위해서!
docker run -d -p 8080:8080 --env-file=env_list.txt --name app pgmjun/cicdstudy:latest

# 사용하지 않는 불필요한 이미지(=dangling 이미지) 삭제
docker rmi -f $(docker images -f "dangling=true" -q) || true
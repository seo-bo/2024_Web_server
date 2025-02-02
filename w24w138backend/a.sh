
#!/bin/bash
set -e  # 오류 발생 시 스크립트 중단

# GitHub 사용자 이름 (필요에 따라 수정)
GH_USER="seo-bo"

# 옮길 기존 리포지터리 목록 (모두 private였던 것들)
repos=(
  "k20230546security"
  "backend20230546"
  "w24w11-song-list"
  "vitejs-vite-mzavrlrn"
  "w24-frontend"
  "w24Backend"
  "w24w138backend"
  "w24w11-the-first-node"
  "i-mweek"
  "d"
  "w24w05thymeleafs"
  "w24w05thymeleaf"
  "w24w04controller"
  "w24w0301table"
  "demo"
)

# 1. 각 리포지터리를 public으로 전환 (GitHub CLI 사용)
echo ">>> 기존 리포지터리들을 public으로 전환합니다."
for repo in "${repos[@]}"; do
    echo "변경 중: $GH_USER/$repo"
    gh repo edit "$GH_USER/$repo" --visibility public
done

# 2. 새 리포지터리 생성 (public)
NEW_REPO="2024_Web_server"
echo ">>> 새 리포지터리 생성: $GH_USER/$NEW_REPO"
gh repo create "$GH_USER/$NEW_REPO" --public --confirm

# 3. 새 리포지터리 클론 및 작업 디렉토리 이동
echo ">>> 새 리포지터리를 클론합니다."
git clone "https://github.com/$GH_USER/$NEW_REPO.git"
cd "$NEW_REPO" || { echo "디렉토리 이동 실패"; exit 1; }

# 4. 각 기존 리포지터리를 서브트리로 추가
echo ">>> 각 기존 리포지터리의 코드를 서브트리로 추가합니다."
for repo in "${repos[@]}"; do
    echo "서브트리 추가 중: $repo"
    # 원격 추가
    git remote add "$repo" "https://github.com/$GH_USER/$repo.git"
    # 원격 fetch
    git fetch "$repo"
    # 서브트리 추가 (각 리포지터리의 내용이 해당 폴더(이름 동일) 아래로 추가됩니다)
    git subtree add --prefix="$repo" "$repo" main --squash
done

# 5. 모든 변경 사항을 원격 리포지터리에 푸시
echo ">>> 최종 변경사항을 원격 리포지터리에 푸시합니다."
git push origin main

echo ">>> 모든 작업 완료: https://github.com/$GH_USER/$NEW_REPO"

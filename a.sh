
#!/bin/bash
set -e  # 오류 발생 시 스크립트 중단

GH_USER="seo-bo"

# 옮길 기존 리포지터리 목록 (이미 public으로 전환되었고, GitHub에 존재)
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

echo ">>> 새 리포지터리 2024_Web_server를 이미 클론한 상태여야 합니다."
echo ">>> 현재 디렉토리: $(pwd)"

# 작업 트리가 깨끗한지 확인 (수정사항이 있으면 종료)
if [[ -n $(git status --porcelain) ]]; then
  echo "작업 디렉토리에 수정사항이 있습니다. 커밋하거나 stash 후 실행하세요."
  exit 1
fi

echo ">>> 각 기존 리포지터리의 코드를 서브트리로 추가합니다."

for repo in "${repos[@]}"; do
    echo "[$repo] 서브트리 추가 중..."
    # 원격 추가 (이미 추가되어 있으면 무시)
    if ! git remote | grep -q "^$repo\$"; then
        git remote add "$repo" "https://github.com/$GH_USER/$repo.git"
    fi
    # 원격 fetch
    git fetch "$repo"
    
    # 사용 가능한 브랜치 결정: 먼저 'main'을 시도, 없으면 'master'
    branch="main"
    if ! git show-ref --verify --quiet "refs/remotes/$repo/$branch"; then
       branch="master"
       if ! git show-ref --verify --quiet "refs/remotes/$repo/$branch"; then
          echo "오류: '$repo' 리포지터리에 'main' 또는 'master' 브랜치가 존재하지 않습니다."
          exit 1
       fi
    fi
    
    # 서브트리 추가: 리포지터리의 내용을 "$repo" 폴더에 병합 (squash 옵션 사용)
    git subtree add --prefix="$repo" "$repo" "$branch" --squash
done

echo ">>> 모든 변경사항을 GitHub에 푸시합니다."
git push origin HEAD

echo ">>> 모든 작업 완료. https://github.com/$GH_USER/2024_Web_server 에서 확인하세요."

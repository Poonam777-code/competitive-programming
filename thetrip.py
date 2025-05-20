import sys

def get_ans(money, n):
    avg = sum(money) / n
    positive_diff = 0
    negative_diff = 0

    for amount in money:
        diff = amount - avg
        if diff > 0:
            positive_diff += diff
        else:
            negative_diff += diff

    return max(positive_diff, -negative_diff)

def main():
    while True:
        line = sys.stdin.readline().strip()
        if not line:
            break
        
        n = int(line)
        if n == 0:
            break
        
        money = []
        for _ in range(n):
            money_line = sys.stdin.readline().strip()
            money.append(float(money_line))
        
        ans = get_ans(money, n)
        print(f"${ans:.2f}")

if __name__ == "__main__":
    main()

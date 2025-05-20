inputText = input().split()

inputList=[]
for index in range(0, len(inputText), 2):
    inputList.append([int(inputText[index]), int(inputText[index + 1])])

def CycleLen(num):
    chainList = [num]
    while num > 1:
        chainList.append(num//2 if num % 2 == 0 else 3*num + 1)
        num = chainList[-1]
    return len(chainList)

for listSet in inputList:
    countList = []
    minRange = min(listSet[0], listSet[1])
    maxRange = max(listSet[0], listSet[1])
    for num in range(minRange, maxRange + 1):
        countList.append(CycleLen(num))

    countList.sort()
    print(listSet[0], listSet[1], countList[-1])
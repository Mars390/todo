theme: /

    state: ЗапускТаймера
        q!: (~включи|~запусти|~поставь|таймер) 
            [на] $AnyText::minutesText [минут|минуты|мин]
            [для] $AnyText::taskText
            
        script:
            log('timerNote: minutesText: ' + $parseTree._minutesText);
            log('timerNote: taskText: ' + $parseTree._taskText);
            
            var minutesMatch = $parseTree._minutesText.match(/\d+/);
            var minutes = minutesMatch ? parseInt(minutesMatch[0]) : 0;
            var task = $parseTree._taskText || "";
            
            if (minutes > 0 && task) {
                // Пробуем другой способ отправки
                $response.data = {
                    type: "timer",
                    minutes: minutes,
                    task: task
                };
                
                $reactions.answer("⏰ Запускаю таймер на " + minutes + " минут для \"" + task + "\"");
            } else {
                $reactions.answer("Скажите, например: 'включи таймер на 20 минут для приготовить макароны'");
            }
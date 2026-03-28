require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
# Подключение javascript обработчиков
require: js/getters.js
require: js/reply.js
require: js/actions.js

# Подключение сценарных файлов
require: sc/addNote.sc
require: sc/doNote.sc
require: sc/deleteNote.sc
require: sc/noteDone.sc
require: sc/timerNote.sc


patterns:
    $AnyText = $nonEmptyGarbage

theme: /
    state: Start
        q!: $regex</start>
        q!: (запусти | открой | вруби) my canvas test
        a: Начнём.
        
        q!: (*таймер* | *включи таймер* | *запусти таймер*)
        script:
            log('entryPoint: timer command detected')
            $context.timerDetected = true
        go!: ../sc/timerNote.sc

    state: Fallback
        event!: noMatch
        script:
            log('entryPoint: Fallback: context: ' + JSON.stringify($context))
        a: Я не понимаю

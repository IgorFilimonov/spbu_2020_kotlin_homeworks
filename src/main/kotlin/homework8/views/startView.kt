package homework8.views

import homework8.GameController
import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import javafx.stage.StageStyle
import tornadofx.*

class StartView : View() {
    private val controller: GameController by inject()
    override val root = vbox {
        this.alignment = Pos.CENTER
        button("Play with yourself") {
            action {
                controller.isBotEnabled = false
                controller.startGame()
            }
        }
        button("Play with bot") {
            prefWidth = 109.0
            action {
                controller.isBotEnabled = true
                find<SettingGameFragment>().openModal(stageStyle = StageStyle.TRANSPARENT)
            }
        }
        setPrefSize(150.0, 150.0)
    }
}

class SettingGameFragment : Fragment() {
    private val controller: GameController by inject()
    private val sideChoice = ToggleGroup()
    private val botDifficulty = ToggleGroup()
    override val root = vbox {
        this.alignment = Pos.CENTER
        label("Choose a side")
        hbox {
            this.alignment = Pos.CENTER
            togglebutton("X", sideChoice) {
                action {
                    controller.playerSide = 'X'
                    controller.botSide = 'O'
                }
            }
            togglebutton("O", sideChoice) {
                action {
                    controller.playerSide = 'O'
                    controller.botSide = 'X'
                }
            }
        }
        label("Choose a bot difficulty")
        hbox {
            vboxConstraints {
                marginBottom = 20.0
            }
            this.alignment = Pos.CENTER
            togglebutton("Easy", botDifficulty) {
                action {
                    controller.isBotEasy = true
                }
            }
            togglebutton("Hard", botDifficulty) {
                action {
                    controller.isBotEasy = false
                }
            }
        }
        button("Start!") {
            action {
                controller.startGame()
                close()
            }
        }
    }
}

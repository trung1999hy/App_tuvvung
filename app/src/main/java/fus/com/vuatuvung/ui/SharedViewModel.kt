package fus.com.vuatuvung.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.lifecycle.HiltViewModel
import fus.com.vuatuvung.data.repository.sharedrepositoy.SharedRepository
import fus.com.vuatuvung.data.repository.soundrepository.SoundRepository
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedRepository: SharedRepository,
    private val soundRepository: SoundRepository
) : ViewModel() {

    fun getInitData(): ArrayList<String> {
        val content = sharedRepository.getInitData()
        if (content.isEmpty()) return arrayListOf()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return try {
            Gson().fromJson(content, type) ?: arrayListOf()
        } catch (e: ClassCastException) {
            arrayListOf()
        } catch (e: JsonIOException) {
            arrayListOf()
        } catch (e: JsonSyntaxException) {
            arrayListOf()
        } catch (e: IllegalStateException) {
            arrayListOf()
        }
    }

    fun setInitData() {
        val list = arrayListOf<String>()
        val re = arrayListOf<String>()
        // start
        list.add("Thiên tài")
        list.add("Mải mê")
        list.add("Khắt khe")
        list.add("Căn dặn")
        list.add("Chín chắn")
        list.add("Văn học")
        list.add("Chủ tịch")
        list.add("Xanh xanh")
        list.add("Chẩn đoán")
        list.add("Mải mê")
        list.add("Đầy ắp")
        list.add("Dân tộc")
        list.add("Văn vở")
        list.add("Trắc trở")
        list.add("Ngờ ngợ")
        list.add("Mũm mĩm")
        list.add("Bóng bay")
        list.add("Đinh vít")
        list.add("Sơ xuất")
        list.add("Xuất sắc")
        list.add("Đường Sá")
        list.add("Lãng mạn")
        list.add("Văn bản")
        list.add("Đua xe")
        list.add("Chia sẻ")
        list.add("Bánh kem")
        list.add("Con ngựa")
        list.add("Cái lồng")
        list.add("Chuồn chuồn")
        list.add("Chơi ngay")
        list.add("Nội dung")
        list.add("Lập trình")
        list.add("Nhân dân")
        list.add("Vô địch")
        list.add("Kết thúc")
        // end 35

        list.add("Xán lạn")
        list.add("Giả thiết")
        list.add("Giả thiết")
        list.add("Căn dặn")
        list.add("Độc giả")
        list.add("Hàm súc")
        list.add("Sáp nhập")
        list.add("Tri thức")
        list.add("Xạo xự")
        list.add("Xạo xự")
        list.add("Đa đoan")
        list.add("Học giả")
        list.add("Lâm sàng")
        list.add("Lí trí")
        list.add("Chân thật")
        list.add("Đúng đắn")
        list.add("Hiện tại")
        list.add("Cổ hủ")
        list.add("Liên quan")
        list.add("Bài viết")
        list.add("Con voi")
        list.add("Con Chim")
        list.add("Cung tên")
        list.add("Voi To")
        list.add("Khải hoàn")
        list.add("Tung tăng")
        list.add("Cảm xúc")
        list.add("Ngọt ngào")
        list.add("Trái cây")
        list.add("Tuân thủ")
        list.add("Đếm giờ")
        list.add("Hồng đen")
        list.add("Danh ngôn")
        list.add("Tự tin")
        list.add("Nặng nhọc")
        list.add("Châm ngôn")
        list.add("Uống rượu")
        list.add("Người ngợm")
        list.add("Bỏ phí")
        list.add("Bữa sáng")
        list.add("Tự Trọng")
        list.add("Ùn tắc")
        list.add("Dự thi")
        list.add("Hội chợ")
        list.add("Đam mê")
        list.add("Thái độ")
        list.add("Vé vàng")
        list.add("Học sinh")
        list.add("Trái ngọt")
        list.add("Đầu tiên")
        list.add("Tin học")
        list.add("Tình bạn")
        list.add("Khó khăn")
        list.add("Thợ xây")
        list.add("Mẫu giáo")
        list.add("Chữ cái")
        list.add("Ngờ ngẩn")
        list.add("Xập xệ")
        list.add("Cãi cọ")
        list.add("Nặng nề")
        list.add("Nhẹ nhàng")
        list.add("Cồng kềnh")
        list.add("Choáng ngợp")
        list.add("Xềnh xệc")
        list.add("Xơ xác")
        list.add("Tan tành")
        list.add("Game over")

        list.forEach {
            val temp = it.replace(" ", "")
            if (temp.length <= 8) re.add(temp)

        }
        Log.d("DataInit", " ${re.size}")
        sharedRepository.initData(Gson().toJson(re))
    }

    fun getLevel() = sharedRepository.getLevel()

    fun addLevel(levelNumber: Int) =
        if (sharedRepository.getLevel() < levelNumber) sharedRepository.addLevel() else Unit

    fun isEnableSound() = sharedRepository.isEnableSound()

    fun setEnableSound(value: Boolean) = sharedRepository.setEnableSound(value)

    fun isEnableSoundFX() = sharedRepository.isEnableSoundFX()

    fun setEnableSoundFX(value: Boolean) = sharedRepository.setEnableSoundFX(value)

    fun playWin() {
        if (isEnableSound())
            soundRepository.playWin()
    }

    fun playLose() {
        if (isEnableSound())
            soundRepository.playLose()
    }

    fun playClick() {
        if (isEnableSoundFX())
            soundRepository.playClick()
    }

    fun playClickWithoutCheck() {
        soundRepository.playClick()
    }

    fun getProgress() = getLevel() * 100 / getInitData().size

}
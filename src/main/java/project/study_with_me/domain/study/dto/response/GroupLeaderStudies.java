package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.dto.GroupLeaderStudy;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupLeaderStudies {

    private List<GroupLeaderStudy> groupLeaderStudies;

    public GroupLeaderStudy createGroupLeaderStudy(Study study, Boolean interest) {
        return GroupLeaderStudy.builder()
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .state(study.getSchedule().getState())
                .type(study.getType())
                .title(study.getTitle())
                .topic(study.getTopic())
                .nowPeople(study.getNowPeople())
                .difficulty(study.getDifficulty())
                .recruitPeople(study.getRecruitPeople())
                .interest(interest)
                .build();
    }
}

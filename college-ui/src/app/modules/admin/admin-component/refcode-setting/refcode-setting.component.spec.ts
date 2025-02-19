import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefcodeSettingComponent } from './refcode-setting.component';

describe('RefcodeSettingComponent', () => {
  let component: RefcodeSettingComponent;
  let fixture: ComponentFixture<RefcodeSettingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RefcodeSettingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RefcodeSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
